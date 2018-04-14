#include <iostream>
#include <vector>
#include <map>
#define maxn 101

class Node {
public:
    Node* fail;
    Node* parent;
    std::vector<Node*> child;
    int id;
    char value;
    int count;

    Node(char value, int id, int count, Node* fail, Node* parent):
            value(value), fail(fail), count(count), id(id), parent(parent) {
        child = std::vector<Node*>();
    }
};

class ACAutomata {
public:
    Node* root;
    int nodeCount;

public:
    explicit ACAutomata(std::vector<std::string>& vec) {
        nodeCount = 0;
        root = new Node(0x00, 0, 0, nullptr, nullptr);
        construct(vec);
    }

    ~ACAutomata() {
        destroy(root);
    }

    void destroy(Node* cNode) {
        while(!cNode->child.empty()) {
            for (unsigned i = 0; i < cNode->child.size(); ++i) {
                destroy(cNode->child.at(i));
            }
        }
        delete cNode;
    }

    void construct(std::vector<std::string>& vec) {
        for (int i = 0; i < vec.size(); ++i) {
            this->insert(vec.at(i));
        }
        for (int j = 0; j < root->child.size(); ++j) {
            root->child.at(j)->fail = root;
            traverseTrieForFail(root->child.at(j));
        }
    }

    void traverseTrieForFail(Node* cn) {
        constructSingleFail(cn);
        if(!cn->child.empty()) {
            for (int i = 0; i < cn->child.size(); ++i) {
                constructSingleFail(cn->child.at(i));
            }
        }
    }

    void constructSingleFail(Node *cn) {
        Node* anc = cn->parent;
        if(cn->fail == nullptr) {
            bool chosen = false;
            while(!chosen) {
                if(anc == root) {
                    cn->fail == root;
                    return;
                }
                for (int i = 0; i < anc->fail->child.size(); ++i) {
                    if(anc->fail->child.at(i)->value == cn->value) {
                        cn->fail = anc->fail->child.at(i);
                        chosen = true;
                    }
                }
                anc = anc->parent;
            }
        }
    }

    void insert(std::string& str) {
        Node* cn = root;
        int pos = 0;
        while (str.size()-1 >= pos) {
            bool matched = false;
            if(!cn->child.empty()) {
                for (int i = 0; i < cn->child.size(); ++i) {
                    if(cn->child.at(i)->value == str.at(pos)) {
                        cn = cn->child.at(i);
                        matched = true;
                    }
                }
                if(!matched) {
                    cn->child.emplace_back(new Node(str.at(pos), 0, 0, nullptr, cn));
                    cn = cn->child.at(cn->child.size());
                }
            }
            pos++;
        }
    }

    std::pair<std::string, int> getFullMatchedString(Node* cn) {
        if(!isLeaf(cn)) return std::pair<std::string, int>("", -1);
        std::string str;
        str.push_back(cn->value);
        Node* anc = cn->parent;
        while (anc != root) {
            str.insert(str.begin(), anc->value);
            anc = anc->parent;
        }
        return std::pair<std::string, int>(str, cn->count);
    }

    std::vector<std::pair<std::string, int>> match(std::string& str) {
        Node* cn = root;
        toolMatch(str, 0, root);

    }

    bool matched(std::string& str) {
        return !match(str).empty();
    }

    std::vector<std::pair<std::string, int>>
    traverseTrieForMatched(Node* cn, std::vector<std::pair<std::string, int>>& vec) {
        if(!isLeaf(cn)) {
            for (int i = 0; i < cn->child.size(); ++i) {
                traverseTrieForMatched(cn->child.at(i), vec);
            }
        } else {
            vec.emplace_back(getFullMatchedString(cn));
        }
    }

    void toolMatch(std::string& str, int pos, Node* cn) {
        if(isLeaf(cn)) {
            cn->count++;
        }
        if(pos+1 == str.size()) return;
        if(!isLeaf(cn)) {
            bool matched;
            for (int i = 0; i < cn->child.size(); ++i) {
                if(cn->child.at(i)->value == str.at(pos)) {
                    toolMatch(str, pos+1, cn->child.at(i));
                    matched = true;
                }
            }
            // not mathed for current, then move to fail
            if(!matched && cn->fail) {
                toolMatch(str, pos, cn->fail);
            }
            // trapped in root
            if(!matched && !cn->fail) {
                // not matched to the end and cannot proceed
                toolMatch(str, pos+1, cn);
            }
        }
    }

    bool isLeaf(Node *cn) {
        return cn->child.empty();
    }
};

class charData {
public:
    char value;
    double prob;
    charData(){
        prob = 0;
        value = 0x41;
    }
};

double getProb(ACAutomata& acm, std::vector<charData>& sample, int i,int j) {
    /**
     * 令d[i][j]表示当前在i节点，还有长为j的路要走且不经过单词节点的概率。
     * 初值为d[i][0]=1，其中i为非单词节点，否则d[i][0]=0。

        d[i][j] = sum( pro(x)*d[k][j-1] )。
        其中x表示从节点i往节点k走的那条边表示字符x，pro(x)是选择字符x的概率。
        且k节点是非单词节点。所以利用记忆话搜索我们就可以计算出d[0][L]。
     */
    int dp[maxn][maxn];

}

int main() {
    int testCases = 0;
    std::cin >> testCases;
    for(int i = 1; i <= testCases; i++) {

        int N = 0;
        std::vector<std::string> patterns;
        while(N--){
            std::string a;
            std::cin>>a;
            patterns.emplace_back(a);
        }

        //construct AC automata
        ACAutomata acm = ACAutomata(patterns);

        int K = 0, L = 0;
        std::cin >> K;
        std::vector<charData> sample = std::vector<charData>();
        while(K--) {
            sample.emplace_back(charData());
            std::cin >> sample.at(sample.size()-1).value;
            std::cin >> sample.at(sample.size()-1).prob;
        }
        std::cin >> L;

        double failProb = 0;
        // begin
        // probability of matched(string) == false
        failProb = getProb(acm, sample, 0, L);
        // end
        std::cout<<"Case #"<<i<<": ";
        printf("%.6lf\n", failProb);
    }
    return 0;
}
