class Dsu{
    private int parent[];
    private int siz[];
    private int n;
    public Dsu(int n){
        this.n=n;
        parent=new int[n];
        siz=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
    }
    public int findParent(int p){
        if(parent[p]==p){
            return p;
        }
        parent[p]=findParent(parent[p]);
        return parent[p];
    }
    public void union(int a,int b){
        int pa=findParent(a);
        int pb=findParent(b);
        if(pa!=pb){
            if(siz[pa]>siz[pb]){
                int temp=pa;
                pa=pb;
                pb=pa;
            }
            parent[pb]=pa;
            siz[pa]+=siz[pb];
        }
    }
}

class Edge implements Comparable<Edge>{
    private int src;
    private int tgt;
    private int strength;
    public Edge(int s,int t,int st){
        src=s;
        tgt=t;
        strength=st;
    }
    public int getSrc(){
       return src; 
    }
    public int getTgt(){
       return tgt; 
    }
    public int getStrength(){
       return strength; 
    }
    public int compareTo(Edge e){
        return e.strength-this.strength;
    }
    public String toString(){
        return this.src+" "+this.tgt+" "+this.strength;
    }
}

class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        int minStren=(int)1e6;
        Dsu dsu=new Dsu(n);
        boolean foundOne=false;
        List<Edge> edgs=new ArrayList<>();
        for(int i=0;i<edges.length;i++){
            if(edges[i][3]==1){
                int paSrc=dsu.findParent(edges[i][0]);
                int paTgt=dsu.findParent(edges[i][1]);
                if(paSrc==paTgt){
                    return -1;
                }
                dsu.union(edges[i][0],edges[i][1]);
                minStren=Math.min(minStren,edges[i][2]);
                foundOne=true;
            }else{
                edgs.add(new Edge(edges[i][0],edges[i][1],edges[i][2]));
            }
        }
        
        Collections.sort(edgs);
        int e;
        List<Integer> zero=new ArrayList<>();
        for(e=0;e<edgs.size();e++){
            //System.out.println(edges);
            int cs=edgs.get(e).getStrength();
            int paSrc=dsu.findParent(edgs.get(e).getSrc());
            int paTgt=dsu.findParent(edgs.get(e).getTgt());
            if(paSrc!=paTgt){
                zero.add(cs);
                dsu.union(paSrc,paTgt);
            }
        }

        for(int i=0;i<n;i++){
            if(dsu.findParent(i)!=dsu.findParent(0)){
                return -1;
            }
        }
        int va=(int)1e6;
        if(zero.size()!=0){
            va=0;
            if(k==0){
                va=zero.get(zero.size()-1);
            }else if(k>=zero.size()){
                va=zero.get(zero.size()-1)*2;
            }else{
                va=Math.min(zero.get(zero.size()-1)*2,zero.get(zero.size()-k-1));
            }
        }
        return Math.min(minStren,va);
    }
}
