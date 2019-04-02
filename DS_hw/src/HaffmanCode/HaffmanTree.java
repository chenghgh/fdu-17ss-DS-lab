package HaffmanCode;
//效率的计算：
//(100*6 + 605*3 + 100*6 + 705*2 + 431*3 + 242*4 + 176*4 + 59*6 + 250*4 + 174*4 + 199*4 + 205*4 + 217*4)/3463 = 3.414
//log14 = 4
//(1 - 3.414/4)*100% = 14.65%
//
class Test {
    public static void main(String[] args) {
        int[] weights = {100,605,100,705,431,242,176,59,250,174,199,205,217};
        HaffmanTree haffmanTree = new HaffmanTree(weights);
        HaffNode[] nodes = haffmanTree.haffmanCode(haffmanTree.haffman(weights));
        for(int i=0;i < haffmanTree.nodeNum;i++){
            System.out.println("weight = " + nodes[i].weight + "   haffmanConde = " + nodes[i].NodeValue);
        }
    }
}

public class HaffmanTree {
    int nodeNum;

    public HaffmanTree(int[] n){
        this.nodeNum = n.length;
    }

    //构造哈夫曼树算法
    public HaffNode[] haffman(int[] weight){
        int n = this.nodeNum;
        int m1,m2,x1,x2; //m1,m2,表示最小的两个权值，x1,x2,表示最小两个权值对应的编号,m1表示最小，m2表示次小

        HaffNode[] nodes = new HaffNode[2 * n - 1];
        for(int i=0;i < 2*n-1;i++){
            HaffNode temp = new HaffNode();
            if(i < n){
                temp.weight = weight[i];
            }
            else{
                temp.weight = 0;
            }
            temp.parent = null;
            temp.flag = 0;
            temp.leftChild = null;
            temp.rightChild = null;
            nodes[i] = temp;
        }

        for(int i=0;i<n-1;i++){
            m1 = m2 = 10000;
            x1 = x2 =0;
            for(int j=0;j< n+i;j++){   //找到权值最小的两个
                if(nodes[j].weight<m1 && nodes[j].flag==0){
                    m2 = m1;
                    x2 = x1;
                    m1 = nodes[j].weight;
                    x1 = j;
                }
                else if(nodes[j].weight<m2 && nodes[j].flag ==0){
                    m2 = nodes[j].weight;
                    x2 = j;
                }
            }

            nodes[x1].parent = nodes[n+i];
            nodes[x2].parent = nodes[n+i];
            nodes[x1].flag = 1;
            nodes[x2].flag = 1;
            nodes[n+i].weight = nodes[x1].weight + nodes[x2].weight;
            nodes[n+i].leftChild = nodes[x1];
            nodes[n+i].rightChild = nodes[x2];
        }
        return nodes;

    }

    //哈弗曼编码算法
    public HaffNode[] haffmanCode(HaffNode[] treeNodes){
        int n = this.nodeNum;

        for(int i=0;i<n; i++){
            String haffCode = "";
            HaffNode node = treeNodes[i];
            while (node.parent != null){
                if (node.parent.leftChild == node){
                    haffCode += "0";
                }
                else if (node.parent.rightChild == node){
                    haffCode += "1";
                }
                node = node.parent;
            }
            treeNodes[i].NodeValue = reverseCode(haffCode);//倒排一下
        }
        return treeNodes;
    }

    public String reverseCode(String s){
        char[] array = s.toCharArray();
        String reverse = "";
        for (int i = array.length - 1; i >= 0; i--){
            reverse += array[i];
        }
        return reverse;
    }
}

