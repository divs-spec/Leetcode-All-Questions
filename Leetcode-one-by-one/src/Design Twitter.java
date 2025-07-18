class Twitter {
    HashMap<Integer, ArrayList<Integer>> follow;
    HashMap<Integer, PriorityQueue<int[]>> posts;
    int time;
    public Twitter() {
        follow = new HashMap<>();
        posts = new HashMap<>();
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {
        if (!follow.containsKey(userId)) {
            follow.put(userId, new ArrayList<>());
        }
        if (posts.containsKey(userId)) {
            posts.get(userId).add(new int[]{tweetId, time++});
            return;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        pq.add(new int[]{tweetId, time++});
        posts.put(userId, pq);
    }

    public List<Integer> getNewsFeed(int userId) {
        if (!follow.containsKey(userId) && !posts.containsKey(userId)) return new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        ArrayList<Integer> list = new ArrayList<>(follow.get(userId));
        list.add(userId);
        int last = -1;
        for (int i = 0; i < list.size(); i++) {
            if (!posts.containsKey(list.get(i)) || list.get(i) == last) continue;
            last = list.get(i);
            PriorityQueue<int[]> temp = new PriorityQueue<>(posts.get(list.get(i)));
            while (!temp.isEmpty())
            pq.add(temp.poll());
        }
        list.remove(list.size() - 1);
        ArrayList<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            int[] tweet = pq.poll();
            int tweetid = tweet[0];
            result.add(tweetid);
            if (result.size() == 10) break;
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        if (!follow.containsKey(followerId)) {
            follow.put(followerId, new ArrayList<>());
        }
        follow.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        ArrayList<Integer> list = follow.get(followerId);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == followeeId) {
                list.remove(i);
                break;
            }
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
