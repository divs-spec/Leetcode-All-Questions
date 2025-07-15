/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    List<Integer> result;
    int i=0;
    public NestedIterator(List<NestedInteger> nestedList) {
        result = new ArrayList<>();
        partialIterator(nestedList);
    }

    public void partialIterator(List<NestedInteger> nestedList)
    {
        for(NestedInteger ni : nestedList)
        {
            if(ni.isInteger())
            {
                result.add(ni.getInteger());
            }
            else
            {
                partialIterator(ni.getList());
            }
        }
    }

    @Override
    public Integer next() {
        int val = result.get(i);
        i++;
        return val;
    }

    @Override
    public boolean hasNext() {
        return i!=result.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
