package Oving4_ver2;

    public class BSTNode<T extends Comparable<T>>{
        private T value;
        private BSTNode<T> left;
        private BSTNode<T> right;

        public BSTNode(T value) {
            this.value = value;
            left = null;
            right = null;
        }

        public boolean add(T value) {
            if (value == this.value)
                return false;
            else if (value.compareTo(this.value) < 0) {
                if (left == null) {
                    left = new BSTNode(value);
                    return true;
                } else
                    return left.add(value);
            } else if (value.compareTo(this.value) > 0) {
                if (right == null) {
                    right = new  BSTNode(value);
                    return true;
                } else
                    return right.add(value);
            }
            return false;
        }



        public void printInorder() {
            if (left != null) {
                System.out.print("(");
                left.printInorder();
                System.out.print(")");
            }
            System.out.print(this.value);
            if (right != null) {
                System.out.print("(");
                right.printInorder();
                System.out.print(")");
            }
        }
    }
