class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int result = 0;
        int countOfPlayers = players.length;
        int countOfTrainers = trainers.length;

        int i = 0, j = 0;
        while(i < countOfPlayers && j < countOfTrainers)
        {
            if(players[i] <= trainers[j])
            {
                result++;
                i++;
                j++;
            }
            else
            {
                j++;
            }
        }

        return result;
    }
}
