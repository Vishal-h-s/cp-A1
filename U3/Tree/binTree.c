#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_QUEUE 10
typedef struct Node
{
    /* data */
    int info;
    struct Node *llink, *rlink;
} Node;
// typedef struct Node * NODE;

Node *createNode(int item)
{
    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->info = item;
    newNode->llink = NULL;
    newNode->rlink = NULL;
    return newNode;
}

void insert(Node **root, int item)
{
    Node *newNode = createNode(item);
    if (*root == NULL)
    {
        *root = newNode;
        return;
    }

    Node *q[MAX_QUEUE], *cur;
    int front = 0, rear = -1;
    q[++rear] = *root;

    while (front <= rear)
    {
        cur = q[front++];
        if (cur->llink != NULL)
        {
            q[++rear] = cur->llink;
        }
        else
        {
            cur->llink = newNode;
            return;
        }

        if (cur->rlink != NULL)
        {
            q[++rear] = cur->rlink;
        }
        else
        {
            cur->rlink = newNode;
            return;
        }
    }
}

Node *getDeepestRightMostNode(Node *root)
{
    Node *q[MAX_QUEUE], *cur;
    int front = 0, rear = -1;
    q[++rear] = root;
    while (front <= rear)
    {
        cur = q[front++];
        if (cur->llink != NULL)
            q[++rear] = cur->llink;
        if (cur->rlink != NULL)
            q[++rear] = cur->rlink;
    }
    return cur;
}

void deleteDeepestRightmostNode(Node *root, Node *dNode)
{
    Node *q[MAX_QUEUE], *cur;
    int front = 0, rear = -1;
    q[++rear] = root;
    while (front <= rear)
    {
        cur = q[front++];
        if (cur == dNode)
        {
            cur = NULL;
            free(dNode);
            return;
        }

        if (cur->rlink != NULL)
        {
            if (cur->rlink == dNode)
            {
                cur->rlink = NULL;
                free(dNode);
                return;
            }
            else
            {
                q[++rear] = cur->rlink;
            }
        }

        if (cur->llink != NULL)
        {
            if (cur->llink == dNode)
            {
                cur->llink = NULL;
                free(dNode);
                return;
            }
            else
            {
                q[++rear] = cur->llink;
            }
        }
    }
}

void delete(Node **root, int target)
{
    if (*root == NULL)
    {
        printf("Tree is empty.\n");
        return;
    }

    if ((*root)->llink == NULL && (*root)->rlink == NULL)
    {
        if ((*root)->info == target)
        {
            free(*root);
            *root = NULL;
            return;
        }
        else
        {
            printf("Node not found.\n");
            return;
        }
    }

    Node *q[MAX_QUEUE], *cur, *targetNode = NULL;
    int front = 0, rear = -1;
    q[++rear] = *root;
    while (front <= rear)
    {
        cur = q[front++];
        if (cur->info == target)
        {
            targetNode = cur;
        }
        if (cur->llink != NULL)
        {
            q[++rear] = cur->llink;
        }
        if (cur->rlink != NULL)
        {
            q[++rear] = cur->rlink;
        }
    }

    if (targetNode != NULL)
    {
        Node *deepestNode = getDeepestRightMostNode(*root);
        targetNode->info = deepestNode->info;
        deleteDeepestRightmostNode(*root, deepestNode);
    }
    else
    {
        printf("Node not found.\n");
    }
}

Node *search(Node *root, int target)
{
    if (root == NULL)
    {
        return NULL;
    }
    Node *q[MAX_QUEUE], *cur;
    int front = 0, rear = -1;
    q[++rear] = root;
    while (front <= rear)
    {
        cur = q[front++];
        if (cur->info == target)
        {
            return cur;
        }
        if (cur->llink != NULL)
        {
            q[++rear] = cur->llink;
        }
        if (cur->rlink != NULL)
        {
            q[++rear] = cur->rlink;
        }
    }
    return NULL;
}

void preorder(Node *root)
{
    if (root == NULL)
    {
        return;
    }
    printf("%d ", root->info);
    preorder(root->llink);
    preorder(root->rlink);
}
void postorder(Node *root)
{
    if (root == NULL)
    {
        return;
    }
    postorder(root->llink);
    postorder(root->rlink);
    printf("%d ", root->info);
}
void inorderTraversal(Node *root)
{
    if (root == NULL)
    {
        return;
    }
    inorderTraversal(root->llink);
    printf("%d ", root->info);
    inorderTraversal(root->rlink);
}

void display(Node *root, int level)
{
    int i;
    if (root == NULL)
    {
        return;
    }
    display(root->rlink, level + 1);
    for (i = 0; i < level; i++)
    {
        printf(" ");
    }
    printf("%d\n", root->info);
    display(root->llink, level + 1);
}

void levelorder(Node *root)
{
    Node *q[MAX_QUEUE], *cur;
    int front = 0, rear = -1;
    q[++rear] = root;
    while (front <= rear)
    {
        cur = q[front++];
        printf("%d ", cur->info);
        if (cur->llink != NULL)
            q[++rear] = cur->llink;
        if (cur->rlink != NULL)
            q[++rear] = cur->rlink;
    }
    printf("\n");
}

// Node *insert(int item, Node *root)
// {
//     Node *temp, *cur, *prev;
//     char
// }

int main()
{
    Node* root = NULL;

    // Inserting nodes
    insert(&root, 20);
    insert(&root, 30);
    insert(&root, 40);
    insert(&root, 50);
    insert(&root, 60);
    insert(&root, 70);
    insert(&root, 80);

    // Inorder traversal
    printf("Inorder traversal of the given Binary Search "
           "Tree is: ");
    inorderTraversal(root);
    printf("\n");

    // Deleting a node
    int deleteValue = 20;
    delete (&root, deleteValue);
    printf("After deletion of %d: ", deleteValue);
    inorderTraversal(root);
    printf("\n");

    // Inserting a new node
    int insertValue = 25;
    insert(&root, insertValue);
    printf("After insertion of %d: ", insertValue);
    inorderTraversal(root);
    printf("\n");

    // Searching for a node
    int target = 25;
    Node* searchResult = search(root, target);
    if (searchResult != NULL) {
        printf("Node %d found in the BST.\n", target);
    }
    else {
        printf("Node %d not found in the BST.\n", target);
    }

    return 0;
}