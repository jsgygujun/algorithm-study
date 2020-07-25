package com.jsgygujun.algorighm.code;

/**
 * @author jsgygujun@gmail.com
 * @since 2020/7/17 3:41 下午
 */
public class MaxHeap {

    private int[] elements;
    private int size;
    private int capacity;

    public MaxHeap(int c) {
        this.capacity = c;
        elements = new int[capacity+1];
        size = 0;
    }

    // 父节点索引
    private int parent(int node) {
        return node / 2;
    }

    // 左孩子索引
    private int leftChild(int node) {
        return node * 2;
    }

    // 右孩子
    private int rightChild(int node) {
        return node * 2 + 1;
    }

    private void swap(int i, int j) {
        int temp = elements[i];
        elements[j] = elements[i];
        elements[i] = temp;
    }

    // 对于下标为i的节点执行上浮操作
    private void shiftUp(int i) {
        while (i > 1 && elements[i] > elements[parent(i)]) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    private void shiftUp2(int i) {
        while (i != 1) {
            if (elements[i] <= elements[parent(i)]) break;
            swap(parent(i), i);
            i = parent(i);
        }
    }

    // 对于下标为node的节点执行下沉操作
    // 思考如下情况：
    // 左孩子节点是否存在？右孩子节点是否存在？
    //              1
    //            /   \
    //           2     3
    //          / \   / \
    //         4   5 6   7
    // size = 8
    private void shiftDown(int node) {
        // 如果判断是否已经下沉到最后一层？ leftChild(node) >= size 则表明node肯定在最后一层
        // 最后一层节点的左孩子下标一定大于等于size
        while (rightChild(node) < size) {
            int n = leftChild(node);
            if (rightChild(node) < size && elements[rightChild(node)] > elements[n]) {
                n = rightChild(node);
            }
            if (elements[node] >= elements[n]) break;
            swap(node, n);
            node = n;
        }
    }

    private void shiftDown2(int i) {
        while (true) {
            int l = leftChild(i) <= size ? leftChild(i) : 0;
            int r = rightChild(i) <= size ? rightChild(i) : 0;
            if (l == 0 && r == 0) break;
            int n = l;
            if (r != 0 && elements[rightChild(i)] > elements[leftChild(i)]) n = r;
            if (elements[i] >= elements[n]) break;
            swap(n, i);
            i = n;
        }
    }

    // 添加一个元素
    // 先把元素加到最后面，然后让他扶起来
    public void insert(int e) {
        if (size + 1 < capacity) {
            size++;
            elements[size] = e;
            shiftUp(size);
        }
    }

    // 删除堆顶元素
    public int pop() {
        int ret = 0;
        if (size > 1) {
            ret = elements[1];
            swap(1, size);
            size--;
            shiftDown(1);
        }
        return ret;
    }

}
