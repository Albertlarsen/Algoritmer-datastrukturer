package Oving4_ver2;

import java.util.Scanner;

class LinkedList {

    static Node head1, head2;
    boolean borrow;

    static class Node {

        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }

    }



    Node addTwoLists(Node first, Node second) {
        Node res = null; // res is head node of the resultant list
        Node prev = null;
        Node temp = null;
        int carry = 0, sum;

        while (first != null || second != null) //while both lists exist
        {
            // Calculate value of next digit in resultant list.
            // The next digit is sum of following things
            // (i)  Carry
            // (ii) Next digit of first list (if there is a next digit)
            // (ii) Next digit of second list (if there is a next digit)
            sum = carry + (first != null ? first.data : 0)
                    + (second != null ? second.data : 0);

            // update carry for next calulation
            carry = (sum >= 10) ? 1 : 0;

            // update sum if it is greater than 10
            sum = sum % 10;

            // Create a new node with sum as data
            temp = new Node(sum);

            // if this is the first node then set it as head of
            // the resultant list
            if (res == null) {
                res = temp;
            } else // If this is not the first node then connect it to the rest.
            {
                prev.next = temp;
            }

            // Set prev for next insertion
            prev = temp;

            // Move first and second pointers to next nodes
            if (first != null) {
                first = first.next;
            }
            if (second != null) {
                second = second.next;
            }
        }

        if (carry > 0) {
            temp.next = new Node(carry);
        }

        return res;
    }
    int getLength(Node node) {
        int size = 0;
        while (node != null)
        {
            node = node.next;
            size++;
        }
        return size;
    }
    Node paddZeros(Node sNode, int diff) {
        if (sNode == null)
            return null;

        Node zHead = new Node(0);
        diff--;
        Node temp = zHead;
        while ((diff--) != 0)
        {
            temp.next = new Node(0);
            temp = temp.next;
        }
        temp.next = sNode;
        return zHead;
    }

    public static Node findNext(Node node, int i){
        if(i == 0){
            return null;
        }
        --i;
        return node.next;
    }
    public static Node findNextNode(Node enNode, int i){
        if(i == 0){
            return enNode;
        }
        Node Node1 = findNext(enNode, i -1);
        return Node1;
    }
    /*public void addNode(LinkedList l, int i, int j){
        Node newNode = new Node(i);
        if(j > 1) l.findNext(head1, j) = new Node(i);
    }*/

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println("");
    }
    Node subtractLinkedListHelper(Node l1, Node l2)
    {
        if (l1 == null && l2 == null && borrow == false)
            return null;

        Node previous = subtractLinkedListHelper((l1 != null) ?
                l1.next : null, (l2 != null) ?
                l2.next : null);

        int d1 = l1.data;
        int d2 = l2.data;
        int sub = 0;

        /* if you have given the value value to
        next digit then reduce the d1 by 1 */
        if (borrow)
        {
            d1--;
            borrow = false;
        }

        /* If d1 < d2 , then borrow the number from
        previous digit. Add 10 to d1 and set
        borrow = true; */
        if (d1 < d2)
        {
            borrow = true;
            d1 = d1 + 10;
        }

        /* subtract the digits */
        sub = d1 - d2;

        /* Create a Node with sub value */
        Node current = new Node(sub);

        /* Set the Next pointer as Previous */
        current.next = previous;

        return current;
    }

    /* This API subtracts two linked lists and
    returns the linked list which shall have the
    subtracted result. */
    Node subtractLinkedList(Node l1, Node l2)
    {
        // Base Case.
        if (l1 == null && l2 == null)
            return null;

        // In either of the case, get the lengths
        // of both Linked list.
        int len1 = getLength(l1);
        int len2 = getLength(l2);

        Node lNode = null, sNode = null;

        Node temp1 = l1;
        Node temp2 = l2;

        // If lengths differ, calculate the smaller
        // Node and padd zeros for smaller Node and
        // ensure both larger Node and smaller Node
        // has equal length.
        if (len1 != len2)
        {
            lNode = len1 > len2 ? l1 : l2;
            sNode = len1 > len2 ? l2 : l1;
            sNode = paddZeros(sNode, Math.abs(len1 - len2));
        }

        else
        {
            // If both list lengths are equal, then
            // calculate the larger and smaller list.
            // If 5-6-7 & 5-6-8 are linked list, then
            // walk through linked list at last Node
            // as 7 < 8, larger Node is 5-6-8 and
            // smaller Node is 5-6-7.
            while (l1 != null && l2 != null)
            {
                if (l1.data != l2.data)
                {
                    lNode = l1.data > l2.data ? temp1 : temp2;
                    sNode = l1.data > l2.data ? temp2 : temp1;
                    break;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
        }

        // After calculating larger and smaller Node,
        // call subtractLinkedListHelper which returns
        // the subtracted linked list.
        borrow = false;
        return subtractLinkedListHelper(lNode, sNode);
    }
    /*public void settInnBakerst(int verdi){
        Node ny = new Node(verdi);
        if(hale!=null) hale.neste = ny;
        else hode= ny;
        hale = ny;
        else head1 = ny;


    }*/
    public static void main(String[] args) {
        //LinkedList list = new LinkedList();
        /*LinkedList run1 = new LinkedList();

       boolean running = true;
        while(running == true){
            System.out.println("Du har følgende valg");
            System.out.println("[0] For å legge til tall");
            System.out.println("[1] For å vise tall");
            Scanner select = new Scanner(System.in);
            int in = select.nextInt();
            switch(in){
                case 0:
                    System.out.println("Hvor mange siffer er det i tallet?");
                    int siffer = Integer.parseInt(select.next());
                    if(siffer!=0){
                        System.out.println("Skriv tall for tall");
                        int adding = 0;
                        adding = Integer.parseInt(select.next());
                        run1.head1 = new Node(adding);
                        for(int i = 0; i < siffer; i++) {

                            run1.head1.findNextNode(head1, siffer)= new Node(adding);
                        }
                        run1.printList(head1);
                        System.out.println(adding + " has been added");
                    }


                    //run1.add(adding);

                    break;
                case 1:
                    run1.printList(head1);
                    break;
            }
        }*/
        LinkedList run1 = new LinkedList();

       boolean running = true;
        /*while(running == true){
            System.out.println("Du har følgende valg");
            System.out.println("[0] For å legge til tall");
            System.out.println("[1] For å vise tall");
            Scanner select = new Scanner(System.in);
            int in = select.nextInt();
            switch(in){
                case 0:
                    System.out.println("Hvor mange siffer er det i tallet?");

                        System.out.println("Skriv tall");
                    int adding = Integer.parseInt(select.next());
                    String number = String.valueOf(adding);
                    char[] digita1 = number.toCharArray();
                    for(int i = 0; i < digital.length; i++){

                    }

                        run1.head1 = new Node(adding);
                        run1.printList(head1);
                        System.out.println(adding + " has been added");
                    }


                    //run1.add(adding);

                    break;
                case 1:
                    run1.printList(head1);
                    break;
            }
        }*/
/*
        // creating first list
        run1.head1 = new Node(adding);
        run1.head1.next = new Node(5);
        run1.head1.next.next = new Node(9);
        run1.head1.next.next.next = new Node(4);
        run1.head1.next*4 = new Node(6);
        System.out.print("First List is ");
        run1.printList(head1);

        // creating seconnd list
        list.head2 = new Node(8);
        list.head2.next = new Node(4);
        System.out.print("Second List is ");
        list.printList(head2);

        // add the two lists and see the result
        Node rs = list.addTwoLists(head1, head2);
        System.out.print("Resultant List is ");
        list.printList(rs);
        */
        /*Node head = new Node(1);
        head.next = new Node(0);
        head.next.next = new Node(0);

        Node head2 = new Node(1);

        LinkedList ob = new LinkedList();
        Node result = ob.subtractLinkedList(head, head2);

        printList(result);*/
    }
}
