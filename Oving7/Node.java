package Oving7;


class Node implements Comparable<Node> {
    Kant kant1;
    Object d;
    int i;


    @Override
    public int compareTo(Node o) {
        return ((Dfs_forgj)o.d).ferdig_tid - ((Dfs_forgj)d).ferdig_tid;
    }
}
