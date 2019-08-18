/* 

Red black tree

- Every node is red or black
- Root is always black
- New insertions are always red
- Every path from root to leaf has the same number of BLACK nodes
- No path can have two consecutive RED nodes
- Nulls are black
*/ 

public class RBT {



    Node root; 
    static final int COUNT = 10;


    public class Node {
        

        Node parent;
        String colour;
        
        int data;

        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.colour = "BLACK";
        }

        public void setBlack() {
            this.colour ="BLACK";
        }
        public void setRed() {

            this.colour = "RED";
        }

        public String toString() {
            return "C: " + colour + " D: " + data;
        }
        public int compareTo(Node other) {

            int lik = 0;
            int mindre = -1;
            int storre = 1;

            Integer data = Integer.valueOf(this.data);
            Integer otherData = Integer.valueOf(other.data);

            return data.compareTo(otherData) == 0 ? lik : data.compareTo(otherData) > 0 ? storre : data.compareTo(otherData) < 0 ? mindre : 0;
        }

        public boolean isLeftChild() {
            return this.parent.left == this;
        }
        public boolean isRightChild() {
            return this.parent.right == this;
        }
    }

    public Node find(int value) {

        if (root == null) {
            return null;
        }
        return find(root, value);

    }

    public Node find(Node n, int value) {
        if (n == null) {
            return null;
        }

        if(n.data == value) {
            return n;
        }

        return value < n.data ? find(n.left,value) : find(n.right, value);

        
    }

    public Node returParent(Node n) {

        if(n.parent == null) {
            return null;
        }
        return n.parent;

    }

    public Node returGrand(Node n) {
        if (n.parent != null) {
            return n.parent.parent;
        }
        return null;
    }


    public void add(Node node, int data) {

        if (data == node.data) {
            return;
        }
        Node localParent = node;

        if (data < node.data) {
            if(node.left == null) {
            
                node.left = new Node(data);

        
               node.left.setRed();
                node.left.parent = localParent;
                rebalance(node.left);
           
                return;
            }
            add(node.left,data);
        }
        if (data > node.data) {
            if(node.right == null) {
                node.right = new Node(data);
                System.out.println("JEG settes inn her " + node.right);
  
             node.right.setRed();
       
                node.right.parent = localParent;
                rebalance(node.right);
                return;

            }
            add(node.right,data);
        }

    }
    public void add(int data) {

        if (root == null) {
            root = new Node(data);
            return;
        }
        add(root, data);

    }


    public void fixRoot() {
        root.setBlack();
    }

    

    public void colorflip(Node n) {
        n.setRed();
        n.left.setBlack();
        n.right.setBlack();
      
    }

    public void checkColor(Node node) {
       
    }

    public void correctTree(Node node) {
  
    }

    public void rotate(Node n) {
        if (n.isLeftChild()) {
            if(n.parent.isLeftChild()) {
                rightRotate(returGrand(n));
                n.setRed();
                n.parent.setBlack();
                if(n.parent.right != null) {
                    n.parent.right.setRed();
                    return;
                }
                rightLeftRotate(n.parent.parent);
                n.setBlack();
                n.right.setRed();
                n.left.setRed();
                return;
            }
        }
  

            if (n.parent.isRightChild()) {
                leftRotate(returGrand(n));
                n.setRed();
                n.parent.setBlack();
                if(n.parent.right!= null) {
                    n.parent.right.setRed();
                    return;
                }
                leftRotate(n.parent);
                rightRotate(n.parent.parent);

                n.setBlack();
                n.right.setRed();
                n.left.setRed();

            }
        

       


    }

    public void rightLeftRotate(Node n) {
       

   
    }

    public void rightRotate(Node n) {
        // faren kommer inn som parameter
        Node newParent = n.parent;
        Node x = n.left;
        n.left = x.right;
        x.right = n;
        x.parent = newParent;
        newParent.left = x;
        n.parent = x;

    }
  
    public void leftRotate(Node n) {


        Node newParent = n.parent;
        Node x = n.right;
        n.right = x.left;
        x.left = n;
        x.parent = newParent;
        newParent.right = x;

        n.parent = x;
   

      
     
    }


    public void rebalance(Node n) {

    
      
        if (n == root ) {
            fixRoot();
            return;
        }
        if (n.parent.colour == "BLACK") {
            // System.out.println(n);
            return;
        }

        Node uncle = getSibling(n.parent);
        Node grand = returGrand(n);
        Node parent = returParent(n);
     

        if(uncle != null && uncle.colour == "RED" && grand != null) {
            colorflip(grand);
            System.out.println("JEG ER GRAND" + grand);
            rebalance(grand);
            return;
        }
        // ingen uncle eller uncle er svart
        if (uncle == null  && n != root || uncle != null && uncle.colour =="BLACK"  ) {
            System.out.println("HEI");

            // left rotate
            if (n.compareTo(n.parent) < 0 && n.parent.compareTo(returGrand(n)) < 0) {
                
                rightRotate(grand);
                grand.setRed();
                parent.setBlack();
                return;
            }
            // right rotate
            if(n.compareTo(n.parent) > 0  && n.parent.compareTo(returGrand(n)) > 0) {
                System.out.println(parent);
                System.out.println(grand);
                System.out.println(uncle);
                leftRotate(grand);
                grand.setRed();
                parent.setBlack();
                return;

            }
        }
    

   

    
    }


    public boolean hasSibling(Node n) {


        return n.parent != null && n.parent.left != null && n.parent.right != null;
    }

    public Node getSibling(Node n) {

      if(hasSibling(n)){
        // hvis n er mindre enn parent, så er sibling n.parent.right, else n.par
        return n.data < n.parent.data ? n.parent.right : n.parent.left;
      }


      return null;


    }


    public boolean isRed(Node n) {

        return n.colour == "RED" ? true  : false;

    }

    public boolean isBlack(Node n) {
        return n.colour == "RED" ? true  : false;
    }

    public void inOrder(Node root) {
        if(root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
        
    }
    public void inOrder() {
        if(root == null) {
            return;
        }
        inOrder(root);

    }
    static void print2DUtil(Node root, int space)  
{  
    // Base case  
    if (root == null)  
        return;  
  
    // Increase distance between levels  
    space += COUNT;  
  
    // Process right child first  
    print2DUtil(root.right, space);  
  
    // Print current node after space  
    // count  
    System.out.print("\n");  
    for (int i = COUNT; i < space; i++)  
        System.out.print(" ");  
    System.out.print(root + "\n");  
  
    // Process left child  
    print2DUtil(root.left, space);  
}  
  
// Wrapper over print2DUtil()  
static void print2D(Node root)  
{  
    // Pass initial space count as 0  
    print2DUtil(root, 0);  
}  
  

    public static void main(String [ ] args) {


        RBT rbt = new RBT();

        rbt.add(3);
        rbt.add(1);
        rbt.add(5);
        rbt.add(7);
        rbt.add(6);
        rbt.add(8);

        System.out.println("------------");
        System.out.println(rbt.root);
        System.out.println(rbt.root.left);
        System.out.println(rbt.root.right);
        System.out.println(rbt.root.right.left);
        System.out.println(rbt.root.right.right);
        System.out.println(rbt.root.right.right.right);
        System.out.println("------------");
    }






}