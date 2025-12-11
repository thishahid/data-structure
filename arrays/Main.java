import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();
        Scanner sn = new Scanner(System.in);
        System.out.println("___________DynamicArray___________");
        int _continue = 0;
        while (_continue == 0){
            System.out.println("____________Array Menu____________");
            System.out.println("1. ADD ELEMENT\n2.REMOVE ELEMENT\n3.REMOVE AT INDEX\n4.DISPLAY\n5.EXIT");
            int choice = sn.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter Element To Be Add: ");
                    int elem = sn.nextInt();
                    arr.add(elem); break;
                case 2:
                    System.out.println("Enter Element To Be Remove: ");
                    int elem2 = sn.nextInt();
                    arr.remove(elem2); break;
                case 3: System.out.println("Enter Element To Be Remove At Index: ");
                    int index = sn.nextInt();
                    arr.removeAt(index); break;
                case 4:
                    System.out.println("Current Array : \n"+ arr.toString());
                    String brk = sn.next();
                    break;
                case 5 :
                    System.out.println("-----------Exited------------");
                    _continue = 1;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

    }
}
