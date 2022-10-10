export NODES=1000000

echo "Java..."
java BinaryTree $NODES
echo
echo "Python..."
python3 BinaryTree.py $NODES
echo
echo "Go..."
./BinaryTree $NODES
echo
echo "JavaScript..."
node BinaryTree.js $NODES
echo
echo "C..."
./BinaryTree_C $NODES
