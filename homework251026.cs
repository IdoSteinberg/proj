using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            int[] arr1 = { 1, 2, 3 };
            ExB(arr1);
            ExD();
        }
        //The function requires the user to input 2 natural numbers with 2 digits and the function prints if they have the same digits or not.
        public static void ExA()
        {
            Console.WriteLine("Enter 2 natural numbers with 2 digits: ");
            int num1 = int.Parse(Console.ReadLine());
            int num2 = int.Parse(Console.ReadLine());
            if ((num1 % 10 == num2 % 10|| num1%10 == num2/10) && (num1/10==num2/10||num1/10==num2%10))
            {
                Console.WriteLine("The numbers have the same digits!");
            }
            else Console.WriteLine("The numbers dont have the same digits");
        }
        //The function uses an array of 3 numbers and checks if the series is going up or not, if it is going up it prints the differences between the numbers.
        public static void ExB(int[] arr)
        {
            if (arr[0] < arr[1] && arr[1] < arr[2])
            {
                Console.WriteLine("The series is totally going up!");
                int diff1 = arr[1] - arr[0];
                int diff2 = arr[2] - arr[1];
                int diff3 = arr[2] - arr[0];
                Console.WriteLine($"The differences between the serieses objects are: {diff1}, {diff2}, {diff3}.");
            }
        }
        //The function requires the user to input a scarf price and calculates how many scarves Tamar can buy with 500 shekels, and if she can buy a poster that costs 7 shekels.
        public static void ExC()
        {
            Console.WriteLine("Enter scarf Price: ");
            double scarfPrice = double.Parse(Console.ReadLine());
            int posterPrice = 7;
            double tamarMoney = 500;
            int count = 0;
            while (tamarMoney >= scarfPrice)
            {
                tamarMoney -= scarfPrice;
                count++;
            }
            Console.WriteLine($"Tamar can buy {count} scarves.");
            if(tamarMoney > posterPrice) Console.WriteLine("Tamar can buy a poster!");
            else Console.WriteLine("Tamar is too broke");
        }
        //The function requires the user to input a natural 2 digit number and checks if it is divisible by 5 and if it has the digit 5 in it.
        public static void ExD()
        {
            Console.WriteLine("Enter a natural 2 digit number: ");
            int num = int.Parse(Console.ReadLine());
            if (num % 5 == 0)
            {
                if (num % 10 == 5 || num / 10 == 5) Console.WriteLine("There is the digit 5 and the number is divisible by 5");
                else Console.WriteLine("The number is divisible by 5");
            }
            else Console.WriteLine("Sorry ):");
        }
    }
}
