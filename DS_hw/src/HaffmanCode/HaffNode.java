package HaffmanCode;

public class HaffNode {
    int weight;  //权值
    int flag = 0; //是否在树上
    String NodeValue;
    HaffNode parent ;
    HaffNode leftChild;
    HaffNode rightChild;

    public HaffNode(){
    }
}
