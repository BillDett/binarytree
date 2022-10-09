from time import perf_counter_ns
from random import randint
import sys

class BinaryTree:

    class Node:
        def __init__(self):
            self.id = self.__generateId(25)
            self.left = None
            self.right = None
    
        def __generateId(self, length):
            chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890'
            result = ''
            for i in range(len(chars)):
                result += chars[randint(0, len(chars)-1)]
            return result

    def __init__(self):
        self.root = None
        self.elapsed = 0
        self.count = 0

    def insertNodes(self, nodes):
        print('Start Building Tree with {:d} nodes.'.format(nodes))
        self.__startTimer()
        for i in range(nodes):
            n = BinaryTree.Node()
            self.root = self.__insert(self.root, n)
        duration = self.__stopTimer()
        print('Done Building Tree in {:f} seconds.'.format(duration/1000.0))

    def __insert(self, root, n):
        if root == None:
            root = n
            return root
        else:
            if n.id > root.id:
                root.right = self.__insert(root.right, n)
            else:
                root.left = self.__insert(root.left, n)
            return root

    def walk(self, show):
        self.count = 0
        print('Start Walking Tree.')
        self.__startTimer()
        self.__visit(self.root, show)
        duration = self.__stopTimer()
        print('Done Walking Tree, saw {:d} Nodes in {:f} seconds.'.format(self.count, duration/1000.0))        

    def __visit(self, n, show):
        if n != None:
            self.__visit(n.left, show)
            self.count += 1
            if show:
                print(n.id)
            self.__visit(n.right, show)


    def __startTimer(self):
        self.elapsed = perf_counter_ns()

    def __stopTimer(self):
        return (perf_counter_ns() - self.elapsed)/1000000


nodes = 1000
showNames = False
if len(sys.argv) > 1:
    nodes = int(sys.argv[1])
if len(sys.argv) > 2:
    showNames = True

bt = BinaryTree()
bt.insertNodes(nodes)
bt.walk(showNames)
