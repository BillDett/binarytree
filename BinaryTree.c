#import <stdio.h>
#import <stdlib.h>
#import <string.h>
#import <time.h>
#import <sys/time.h>

typedef struct node {
    char *id;
    struct node *left;
    struct node *right;
} Node;

Node *root;
long long elapsed;
int count;

char *generateId(int length) {
    char *chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    int charlen = strlen(chars);
    char *result = (char *) malloc(sizeof(char) * length);
    for (int i=0; i < length; i++) {
        result[i] = chars[rand()%charlen];
    }
    return result;
}

long long getMillis(void) {
    struct timeval tv;
    gettimeofday(&tv, NULL);
    return (((long long)tv.tv_sec)*1000) + (tv.tv_usec/1000);
}

void startTimer() {elapsed = getMillis(); }
long long stopTimer() { return getMillis() - elapsed; }


Node *newNode() {
    Node *n = (Node *) malloc(sizeof(Node));
    n->id = generateId(25);
    n->left = NULL;
    n->right = NULL;
    return n;
}

Node *insert(Node *root, Node *n) {
    if ( root == NULL ) {
        root = n;
        return root;
    } else {
        if ( strcmp(n->id, root->id) > 0) {
            root->right = insert(root->right, n);
        } else {
            root->left = insert(root->left, n);
        }
        return root;
    }
}

void insertNodes(int nodes) {
    printf("Start Building Tree with %d nodes.\n", nodes);
    startTimer();
    for (int i=0; i < nodes; i++) {
        Node *n = newNode();
        root = insert(root, n);
    }
    long long duration = stopTimer();
    printf("Done Building Tree in %f seconds.\n", duration/1000.0);
}

void visit(Node *n, int show) {
    if ( n != NULL ) {
        visit(n->left, show);
        count++;
        if (show) {
            printf("%s\n", n->id);
        }
        visit(n->right, show);
    }
}

void walk(int show) {
    count = 0;
    printf("Start Walking Tree.\n");
    startTimer();
    visit(root, show);
    long long duration = stopTimer();
    printf("Done Walking Tree, saw %d Nodes in %f seconds.\n", count, duration/1000.0);
}

int main(int argc, char** argv) {

    // Seed the random number generator
    time_t t;
    srand((unsigned) time(&t));

    int nodes = 1000;
    int showNames = 0;

    if (argc > 1) {
        nodes = atoi(argv[1]);
    }
    if (argc > 2) {
        showNames = 1;
    }

    insertNodes(nodes);
    walk(showNames);

}