package com.nicai.algorithm.leetcode.editor.cn;
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学

import java.util.Objects;
import java.util.Stack;

/**
 * @author nijixucai
 */
class P2AddTwoNumbers {
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            return addTwoNumbersByStack(l1, l2);
        }

        /**
         * 递归调用addTwoNumbers，直到两个链表的末尾并且上个数字之和小于0
         *
         * @param l1    链表1
         * @param l2    链表2
         * @param carry 是否进位
         * @return 链表结果
         */
        ListNode addTwoNumbers(ListNode l1, ListNode l2, boolean carry) {
            ListNode listNode = new ListNode(0);
            if (isNull(l1) && isNull(l2) && !carry) {
                return null;
            }
            int i = getVal(l1) + getVal(l2) + (carry ? 1 : 0);
            listNode.val = i % 10;
            listNode.next = addTwoNumbers(isNull(l1) ? null : l1.next, isNull(l2) ? null : l2.next, i >= 10);
            return listNode;
        }

        /**
         * 使用栈解决问题
         *
         * @param l1 链表1
         * @param l2 链表2
         * @return 链表结果
         */
        ListNode addTwoNumbersByStack(ListNode l1, ListNode l2) {
            Stack<Integer> stack1 = nodeToStack(l1);
            Stack<Integer> stack2 = nodeToStack(l2);
            Stack<Integer> result = new Stack<>();
            while (!stack1.isEmpty() || !stack2.isEmpty()) {
                Integer value1 = stack1.isEmpty() ? 0 : stack1.pop();
                Integer value2 = stack2.isEmpty() ? 0 : stack2.pop();
                int temp = value1 + value2;
                if (temp >= 10) {
                    result.push(temp % 10);
                    if (stack1.isEmpty()) {
                        stack1.push(1);
                    } else {
                        stack1.push(stack1.pop() + 1);
                    }
                } else {
                    result.push(temp);
                }
            }
            return stackToNode(result);
        }

        /**
         * 栈转换成列表
         *
         * @param stack 保存结果的栈
         * @return
         */
        private ListNode stackToNode(Stack<Integer> stack) {
            ListNode result = new ListNode(stack.pop());
            while (!stack.isEmpty()) {
                ListNode listNode = new ListNode(stack.pop());
                listNode.next = result;
                result = listNode;
            }
            return result;
        }

        /**
         * 获取链表的数值
         *
         * @param l1 链表主节点
         * @return 整个链表的值
         */
        private Stack<Integer> nodeToStack(ListNode l1) {
            Stack<Integer> stack = new Stack<>();
            while (Objects.nonNull(l1)) {
                stack.push(l1.val);
                l1 = l1.next;
            }
            Stack<Integer> result = new Stack<>();
            while (!stack.isEmpty()) {
                result.push(stack.pop());
            }
            return result;
        }


        int getVal(ListNode listNode) {
            return isNull(listNode) ? 0 : listNode.val;
        }

        boolean isNull(ListNode listNode) {
            return null == listNode;
        }

        /**
         * 解决版本一
         * 使用数字相加，无法支持特大数字
         *
         * @param l1
         * @param l2
         * @return
         */
        ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
            long num1 = listNodeToNum(l1);
            long num2 = listNodeToNum(l2);
            long sum = num1 + num2;
            return numberToListNode(sum);
        }

        /**
         * 将ListNode转换为int数字
         * 如：(2 -> 4 -> 3) -> 243
         * 如：(5 -> 6 -> 4) -> 564
         *
         * @param l1 需要转换的ListNode
         * @return 转换后的数字
         */
        long listNodeToNum(ListNode l1) {
            ListNode next = l1.next;
            int val = l1.val;
            // 如果还有下一个数字
            if (null != next) {
                return 10 * listNodeToNum(next) + val;
            }
            return val;
        }

        /**
         * 将int数字倒序转换为ListNode
         * 如：807->（7->0->8）
         *
         * @param num 待转换的数字
         * @return ListNode
         */
        ListNode numberToListNode(long num) {
            String numStr = String.valueOf(num);
            char[] chars = numStr.toCharArray();
            return getListNode(chars, chars.length - 1);
        }

        ListNode getListNode(char[] chars, int startIndex) {
            ListNode result = null;
            if (startIndex >= 0) {
                result = new ListNode(Integer.valueOf(String.valueOf(chars[startIndex])));
                result.next = getListNode(chars, startIndex - 1);
            }
            return result;
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)
    class ListNode {
        /**
         * 当前的值
         */
        int val;
        /**
         * 下一个值
         */
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }
}