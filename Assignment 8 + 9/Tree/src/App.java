import java.util.Random;

public class App {
   public App() {
   }

   private static long search(int var0, int var1) {
      Random var2 = new Random();
      int[] var3 = new int[var0];
      BinaryTree var4 = new BinaryTree();

      int var6;
      for(int var5 = 0; var5 < var0; ++var5) {
         var6 = var2.nextInt(var0 * 2);
         var3[var5] = var6;
         var4.add(var6);
      }

      int[] var10 = new int[var1];

      for(var6 = 0; var6 < var1; ++var6) {
         var10[var6] = var2.nextInt(var0 * 2);
      }

      long var11 = System.nanoTime();

      for(int var8 = 0; var8 < var1; ++var8) {
         int var9 = var10[var8];
         if (var4.lookup(var9)) {
            break;
         }
      }

      long var12 = System.nanoTime();
      return var12 - var11;
   }

   public static void main(String[] args) throws Exception {

      BinaryTree var1 = new BinaryTree();
      var1.add(10);
      var1.add(7);
      var1.add(12);
      var1.add(5);
      var1.add(8);
      var1.add(11);
      var1.add(15);
      var1.add(3);
      var1.add(6);
      var1.add(9);
      var1.add(14);
      var1.add(20);
      var1.add(13);

      var1.sequence();
      System.out.println();;
      //var1.printDFS();
   }
}
