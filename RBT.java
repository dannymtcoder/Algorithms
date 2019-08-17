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
            return "Colour: " + colour + "Data: " + data;
        }
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
                node.left.parent = localParent;
                rebalance(node.left);
                return;
            }
            add(node.left,data);
        }
        if (data > node.data) {
            if(node.right == null) {
                node.right = new Node(data);
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
        n.parent.setBlack();
        getSibling(n.parent).setBlack();
        n.parent.parent.setRed();
        root.setBlack();
        
        // parent becomes red, children black
    }

    public void rotate(Node n) {
        // parent becomes black, children become red
        System.out.println("Parent");
        System.out.println(n.parent);
        n.setBlack();
    n.left.setRed();
        n.right.setRed();


    }

    public void rightLeftRotate(Node n) {
        Node hoyre = returGrand(n).right;
        Node venstre = returGrand(n);

        returGrand(n).parent.right = n;
        n.parent = returGrand(n).parent;

        n.right = hoyre;
        n.left = venstre;
        hoyre.parent = n; venstre.parent = n;

   
    }
    public void leftRotate(Node n) {

        
       Node left =  returGrand(n).parent.right;

       returGrand(n).parent.right = n.parent;
       n.parent = returGrand(n).parent;

       n.parent.left = left;
       left.parent = n.parent;

     
    }


    public void rebalance(Node n) {

        // Inserted node is always red.
        n.setRed();

    
        if (hasSibling(n.parent)) {
            // colour til tanten
            String colour = getSibling(n.parent).colour;

            // breaks the rule:  No path can have two consecutive RED nodes
            if (colour == "RED"){
                System.out.println("colorflip");
                colorflip(n);
                return;
            }

      
        }


 
        if(n.parent.colour == "RED" && hasSibling(n.parent) == false) {
            if (n.data < n.parent.data) {
                rightLeftRotate(n);
                
                rotate(n);
            }
            if (n.data > n.parent.data && returGrand(n) != null) {
                System.out.println("Hello")
                leftRotate(n);
                rotate(n);
            }
        } 


       



        return;
        // if (n != null && n != root && n.parent.colour == "RED") {
        //     System.out.println("HEI");
        // }
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