public class Main {

        public static void main(String[] args) throws Exception {

            //创建压缩对象

            Compress compress = new Compress();

            //统计文件中0-255出现的次数

            compress.countTimes("C:\\Users\\15242\\Desktop\\Project 1\\Test Cases\\test1 - single file\\27.mp3");

            //构造哈夫曼树，并得到根节点

            HuffmNode root=compress.createTree();

            //得到哈夫曼编码

            compress.getHuffmCode(root, "");

            //压缩文件

            compress.compress("C:\\Users\\15242\\Desktop\\Project 1\\Test Cases\\test1 - single file\\27.mp3",

                    "C:\\Users\\15242\\Desktop\\Project 1\\Test Cases\\test1 - single file\\27.cc");

            //DeCompress deCompress = new DeCompress("C:\\Users\\15242\\Desktop\\Project 1\\Test Cases\\test1 - single file\\2.cc","C:\\Users\\15242\\Desktop\\Project 1\\Test Cases\\test1 - single file\\2-.pdb");
            //deCompress.decompress();

        }


}
