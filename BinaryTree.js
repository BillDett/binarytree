var util = require('util');

class Node {

    constructor() {
        this.id = this.generateId(25);
        this.left = null;
        this.right = null;
    }

    generateId(length) {
        var result = '';
        var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
        var charlen = chars.length
        for (let i=0; i < charlen; i++ ) {
            result += chars.charAt(Math.floor(Math.random() * charlen));
        }
        return result;
    }

}

class BinaryTree {

    constructor() {
        this.root = null;
        this.elapsed = 0;
        this.count = 0;
    }

    insertNodes(nodes) {
        console.log(util.format('Start Building Tree with %d nodes.', nodes));
        this.startTimer();
        for (let i=0; i < nodes; i++) {
            var n = new Node()
            this.root = this.insert(this.root, n);
        }
        var duration = this.stopTimer();
        console.log(util.format('Done Building Tree in %f seconds.', duration/1000.0));
    }

    insert(root, n){
        if ( root == null ) {
            root = n;
            return root;
        } else {
            if ( n.id > root.id ) {
                root.right = this.insert(root.right, n);
            } else {
                root.left = this.insert(root.left, n);
            }
            return root;
        }
    }

    walk(show) {
        this.count = 0;
        console.log('Start Walking Tree.');
        this.startTimer();
        this.visit(this.root, show);
        var duration = this.stopTimer();
        console.log(util.format('Done Walking Tree, saw %d Nodes in %f seconds.', this.count, duration/1000.0));
    }

    visit(n, show) {
        if ( n != null ) {
            this.visit(n.left, show);
            this.count++;
            if (show) {
                console.log(util.format('%s', n.id));
            }
            this.visit(n.right, show);
        }
    }

    startTimer() { this.elapsed = Date.now(); } // already in millis
    stopTimer() { return Date.now() - this.elapsed; }

}

var nodes = 1000000;
var showNames = false;

const theArgs = process.argv.slice(2);  // Remove first two args ('node' and name of this file)
if (theArgs.length > 0) {
    nodes = parseInt(theArgs[0]);
}
if (theArgs.length > 1) {
    showNames = true;
}

var bt = new BinaryTree();
bt.insertNodes(nodes);
bt.walk(showNames);