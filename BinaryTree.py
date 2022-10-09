from time import perf_counter_ns
from random import randint
import sys

class BinaryTree:

    class Node:
        def __init__(self, id):
            self.id = id
            self.left = None
            self.right = None

    chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890'

    def __init__(self):
        self.root = None
        self.elapsed = 0

    def create(self, levels):
        print('Start Building Tree with {:d} levels.'.format(levels))
        self.__startTimer()
        self.root = self.__buildTree(levels)
        duration = self.__stopTimer()
        print('Done Building Tree in {:f} seconds.'.format(duration/1000.0))

    def walk(self):
        print('Start Walking Tree.')
        self.__startTimer()
        self.root = self.__visit(self.root)
        duration = self.__stopTimer()
        print('Done Walking Tree in {:f} seconds.'.format(duration/1000.0))        

    def __buildTree(self,level):
        if level == 0:
            return None
        else:
            n = BinaryTree.Node(self.__generateId(25))
            n.left = self.__buildTree(level-1)
            n.right = self.__buildTree(level-1)
            return n

    def __visit(self, n):
        if n != None:
            n.id.lower()
            self.__visit(n.left)
            self.__visit(n.right)


    def __startTimer(self):
        self.elapsed = perf_counter_ns()

    def __stopTimer(self):
        return (perf_counter_ns() - self.elapsed)/1000000

    def __generateId(self, length):
        result = ''
        for i in range(len(BinaryTree.chars)):
            result += BinaryTree.chars[randint(0, len(BinaryTree.chars)-1)]
        return result

levels = 5
if len(sys.argv) > 1:
    levels = int(sys.argv[1])

bt = BinaryTree()
bt.create(levels)
bt.walk()
