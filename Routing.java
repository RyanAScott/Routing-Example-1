import java.io.*;

public class Routing{
    // verify function search the next node and repeat till it not got the destination address 
    // or NodeCount will become zero
    static void verify(int m[][],int n,int pn,int s,int d,int h){
        /* 
        m - network routing table 
        n - number of nodes 
        pn - previous node 
        s - new source node
        d - destination node 
        h - time remain to live in network
        */
        int i=s;
        if(h==0||s==d)
            return;
        for(int j=1;j<=n;j++){
            if(m[i][j]==1&&j!=i&&j!=pn)
                System.out.print(j+" ");
        }// for
        System.out.println("\n\n");
        for(int j=1;j<=n;j++){
            if(m[i][j]==1&&j!=i&&j!=pn)
            verify(m,n,s,j,d,(h-1));
        }// for
    }// verify

    static void Flood(int m[][],int n,int s,int d,int h){
        System.out.println("\n\n"+s+"\n\n");
        verify(m,n,0,s,d,h);
    }// Flood

    static int NodeCount(int m[][],int n){
        // NodeCount function will decide the life time for frame
        int h=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                h+=m[i][j];
            }// for
        }// for
        h-=n;
        h=((h%2)==0)?(h/2):(h/2+1);
        return h;
    }// NodeCount

    public static void NetworkRouting(int m[][],int n){
        // NetworkRouting function show the network in matrix form which you entered
        System.out.println("\n\nNetwork Matrix 1st row and colomn showing nodes id\n\n");
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                if(i==0&&j==0)
                    System.out.print("nodes- ");
                else if(j==0)
                    System.out.print(m[i][j]+" ");
                else
                    System.out.print(m[i][j]+" ");
            }// for
            System.out.println();
        }// for
    }// NetworkRouting

    public static void main(String args[])throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Number of nodes in network: ");
        int n=Integer.valueOf(br.readLine());
        // 1 stands for a connection and 0 stands for not connection between two nodes
        System.out.println("Enter the connections with(0 and 1):");
        int [][]network=new int[n+1][n+1];
        // first row and colmn contains nodes number starting from 1 to n 
        for(int i=1;i<=n;i++){
            network[0][i]=i;
            network[i][0]=i;
        }// for
        // this is matrix containing the inforation about connection between nodes
        for(int i=1;i<=n;i++){
            System.out.println("Row no: "+(i));
            for(int j=1;j<=n;j++){
                System.out.print((j)+": ");
                int c=Integer.valueOf(br.readLine());
                if(c==0||c==1) network[i][j]=c;
                else {
                    System.out.println("Please Enter only 0 or 1.\n Enter again.");
                    j-=1;
                }// else
            }// for
        }// for
        NetworkRouting(network,n);
        int h=NodeCount(network,n);//h used to count maximum time to live frame
        System.out.println("Maximum Life of frame : "+h);
        int c; //just a variable used to determine error
        do{
            System.out.print(" Enter the source node id : ");
            c=Integer.valueOf(br.readLine());
            if(c==0||c>n)
            System.out.print("Entered wrong id number not available in network.try again.\n Re");
        }while(c==0||c>n);// do while
        int s=c;//source node id variable
        do{
            System.out.print(" Enter the destination node id : ");
            c=Integer.valueOf(br.readLine());
            if(c==0||c>n)
                System.out.print("Please enter a valid id number that is available in network.\n Re");
        }while(c==0||c>n); // do while
        int d=c;//destination node id variable
        Flood(network,n,s,d,h);
    }// main
}// Routing Class