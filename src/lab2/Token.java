package lab2;

import jdk.nashorn.internal.ir.IfNode;

/**
 * Created by 111 on 31.10.2016.
 */
public enum Token {
    If{
        @Override
        public String toString() {
            return "if ";
        }
    },Then,Else,var,cnst,
    nEqual{
        @Override
        public String toString() {
            return "<>";
        }
    },
    ass{
        @Override
        public String toString() {
            return "=";
        }
    },
    mul{
        @Override
        public String toString() {
            return "*";
        }
    },
    div{
        @Override
        public String toString() {
            return "/";
        }
    },
    sum{
        @Override
        public String toString() {
            return "+";
        }
    },
    sub{
        @Override
        public String toString() {
            return "-";
        }
    };

    public char toChar(char c) {
        return c;
    }
    public int toNum(int num) {
        return num;
    }
}
