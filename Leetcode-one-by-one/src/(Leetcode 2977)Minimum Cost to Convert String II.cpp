#include <bits/stdc++.h>
using namespace std;

class TrieNode {
public:
    TrieNode* next[26];
    int index;

    TrieNode() {
        memset(next, 0, sizeof(next));
        index = -1;
    }
};

class Solution {
private:
    int idx = 0;

    void insert(const string& s, TrieNode* root) {
        for (char c : s) {
            int t = c - 'a';
            if (!root->next[t]) root->next[t] = new TrieNode();
            root = root->next[t];
        }
        if (root->index == -1)
            root->index = idx++;
    }

    int getIndex(const string& s, TrieNode* root) {
        for (char c : s) {
            root = root->next[c - 'a'];
        }
        return root->index;
    }

public:
    long long minimumCost(string source, string target,
                          vector<string>& original,
                          vector<string>& changed,
                          vector<int>& cost) {

        TrieNode* root = new TrieNode();

        // Insert all strings into trie
        for (auto& s : original) insert(s, root);
        for (auto& s : changed) insert(s, root);

        int nNodes = idx;
        const int INF = 1e9;

        vector<vector<int>> dist(nNodes, vector<int>(nNodes, INF));
        for (int i = 0; i < nNodes; i++) dist[i][i] = 0;

        // Direct transformation costs
        for (int i = 0; i < cost.size(); i++) {
            int u = getIndex(original[i], root);
            int v = getIndex(changed[i], root);
            dist[u][v] = min(dist[u][v], cost[i]);
        }

        // Floyd–Warshall
        for (int k = 0; k < nNodes; k++)
            for (int i = 0; i < nNodes; i++)
                if (dist[i][k] != INF)
                    for (int j = 0; j < nNodes; j++)
                        if (dist[k][j] != INF)
                            dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);

        int n = source.size();
        vector<long long> dp(n + 1, LLONG_MAX);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == LLONG_MAX) continue;

            // Single character match
            if (source[i] == target[i])
                dp[i + 1] = min(dp[i + 1], dp[i]);

            TrieNode* p1 = root;
            TrieNode* p2 = root;

            for (int j = i; j < n; j++) {
                p1 = p1->next[source[j] - 'a'];
                p2 = p2->next[target[j] - 'a'];

                if (!p1 || !p2) break;

                if (p1->index != -1 && p2->index != -1) {
                    int c = dist[p1->index][p2->index];
                    if (c != INF) {
                        dp[j + 1] = min(dp[j + 1], dp[i] + c);
                    }
                }
            }
        }

        return dp[n] == LLONG_MAX ? -1 : dp[n];
    }
};
