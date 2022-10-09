package main

import (
	"fmt"
	"math/rand"
	"time"
	"strconv"
	"os"
)

type BinaryTree struct {
	root		*Node
	elapsed		int64
	count		int
}

type Node struct {
	id			string
	left		*Node
	right		*Node
}

func newBinaryTree() *BinaryTree {
	b := &BinaryTree{}
	return b
}

func newNode() *Node {
	n := &Node{}
	n.id = generateId(25)
	return n
}

func (bt *BinaryTree) insertNodes(nodes int) {
	fmt.Printf("Start Building Tree with %d nodes.\n", nodes)
	bt.startTimer()
	for i:=0; i < nodes; i++ {
		n := newNode()
		bt.root = bt.insert(bt.root, n)
	}
	duration := bt.stopTimer()
	fmt.Printf("Done Building Tree in %f seconds.\n", float64(duration)/1000.0)
}

func (bt *BinaryTree) insert(root *Node, n *Node) *Node {
	if (root == nil) {
		root = n
		return root
	} else {
		if (n.id > root.id) {
			root.right = bt.insert(root.right, n)
		} else {
			root.left = bt.insert(root.left, n)
		}	
		return root
	}
}

func (bt *BinaryTree) walk(show bool) {
        bt.count = 0
        fmt.Printf("Start Walking Tree.\n")
        bt.startTimer()
        bt.visit(bt.root, show)
        duration := bt.stopTimer()
        fmt.Printf("Done Walking Tree, saw %d Nodes in %f seconds.\n", bt.count, float64(duration)/1000.0)
}

func (bt *BinaryTree) visit(n *Node, show bool) {
	if (n != nil) {
		bt.visit(n.left, show)
		bt.count++
		if (show) {
			fmt.Printf("%s\n", n.id)
		}
		bt.visit(n.right, show)
	}
}

func (bt *BinaryTree) startTimer() {
    bt.elapsed = time.Now().UnixNano()
}

func (bt *BinaryTree) stopTimer() int64 {
    return (time.Now().UnixNano() - bt.elapsed)/1000000
}

func generateId(length int) string {
	const chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
	b := make([]byte, length)
    for i := range b {
        b[i] = chars[rand.Intn(len(chars))]
    }
    return string(b)
}

func main() {
	nodes := 1000
	showNames := false

	if (len(os.Args) > 1) {
		nodes, _ = strconv.Atoi(os.Args[1])
	}
	if (len(os.Args) > 2) {
		showNames = true
	}
	
	bt := newBinaryTree()
	bt.insertNodes(nodes)
	bt.walk(showNames)
}
