using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Ex1(1, 1);
            Console.ReadKey();
        }
        //Solves exercise 1
        public static void Ex1(double r, double a)
        {
            double shortSide = Math.Sqrt((Math.Pow(2*r, 2) - Math.Pow(a, 2)));
            double Area = Math.PI * Math.Pow(r, 2);
            double bigSquare = Math.Pow(2*r, 2);
            double smallRectangle = a * shortSide;
            double val1 = Math.Round(Math.Pow(shortSide, 2));
            double val2 = bigSquare / 2;
            double val3 = Math.Sqrt(Area);
            double minNum = Math.Min(Math.Min(val1, val2), val3);
            double maxNum = Math.Max(Math.Max(val1, val2), val3);
            double midNum = Math.Min(Math.Max(val1, val2), val3);
            Console.WriteLine($"Numbers are: {minNum}, {midNum}, {maxNum}");
        }
        //Calculates solutions for Quadratic Formula
        public static void QuadraticFormula(double a, double b, double c)
        {
            if (a == 0)
            {
                Console.WriteLine("Not a Quadratic Formula");
                return;
            }
            double disc = (Math.Pow(b, 2) - 4 * a * c);
            if (disc < 0) Console.WriteLine("No Solution");
            else if (disc == 0) Console.WriteLine($"Single solution is {-b/2*a}");
            else
            {
                Console.Write("There are two solutions: ");
                double sol1 = (-b + Math.Sqrt(disc)) / 2*a;
                double sol2 = (-b - Math.Sqrt(disc)) / 2*a;
                Console.Write($"solution 1: {sol1}, solution 2: {sol2}");
            }
        }
    }
}