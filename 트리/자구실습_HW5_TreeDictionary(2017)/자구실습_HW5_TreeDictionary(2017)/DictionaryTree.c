/*
	�ۼ��� : 2021.10.04
	�ۼ��� : ��ξ�
	���α׷��� : ���Ͽ��� �ڷḦ �о� ������������ �����ϴ� ���α׷�
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <memory.h>

int count = 0;

// �ǹ� ���Ḯ��Ʈ
typedef struct ListNode {
	char* meaning;
	struct ListNode* link;
}ListNode;

//������ ����
typedef struct element {
	char* word; // Ű�ʵ�
	ListNode *meanlist;
}element;

typedef struct TreeNode {
	element key;
	struct TreeNode* left, *right;
}TreeNode;

// ���� e1 < e2 �̸� -1 ��ȯ
// ���� e1 == e2 �̸� 0 ��ȯ
// ���� e1 > e2 �̸� 1 ��ȯ
int compare(element e1, element e2)
{
	return strcmp(e1.word, e2.word);
}

// Ʈ�� ����� ������ ���� �Լ�
int get_node_count(TreeNode* node)
{
	int count = 0;

	if (node != NULL)
		count = 1 + get_node_count(node->left) +
		get_node_count(node->right);

	return count;
}

// Ʈ������� �ǹ� ���Ḯ��Ʈ�� �������� ����
ListNode* insert_last(ListNode* head, char* s)
{
	ListNode* p = (ListNode*)malloc(sizeof(ListNode));
	p->meaning = (char*)malloc(strlen(s) + 1);
	strcpy(p->meaning, s);
	p->link = NULL;

	if (p == NULL) {
		printf("Memory error");
		return;
	}


	//��������Ͱ� NUll�̸�(�� ����Ʈ�� ������� ���)
	if (head == NULL) {
		head = p;
	}

	//�׷��� ������
	else {
		ListNode* temp = head;

		while (temp->link != NULL)
			temp = temp->link;

		temp->link = p;
	}

	return head;
}

// Ʈ������� �ǹ� ���Ḯ��Ʈ ���
void printlist(ListNode* p, int c)
{
	ListNode* tmp = p;
	// c == 1�̸� Ʈ���� ������ ��尡 �ƴ� ���
	if (c == 1) {
		while (tmp != NULL) {
			//���Ḯ��Ʈ�� ������ ��� ��ǥ ����
			printf("%s, ", tmp->meaning);
			tmp = tmp->link;
		}
	}
	else {  // Ʈ���� ������ �����
		while (tmp != NULL) {
			if (tmp->link == NULL) {
				printf("%s", tmp->meaning);
				tmp = tmp->link;
			}
			else {
				printf("%s, ", tmp->meaning);
				tmp = tmp->link;
			}
		}
	}
}

//���� Ž�� Ʈ�� ��� �Լ� (������ȸ)
void display(TreeNode* p, int node)
{
	if (p != NULL) {
		ListNode* tmp = p->key.meanlist;
		display(p->left, node);
		printf("%s ", p->key.word);
		/**/
		if (count != node - 1) { // ������ ��尡 �ƴϸ�
			count++;
			printlist(tmp, 1);
		}
		else { // ������ ����̸�(������ �ǹ� ��ǥ����)
			count++;
			printlist(tmp, 2);
		}

		display(p->right, node);
	}
}

// ���� Ž�� Ʈ�� Ž�� �Լ�
TreeNode* search(TreeNode* root, element key)
{
	TreeNode* p = root;
	while (p != NULL) {
		if (compare(key, p->key) == 0)
			return p;
		else if (compare(key, p->key) < 0)
			p = p->left;
		else if (compare(key, p->key) > 0)
			p = p->right;
	}
	return p;  // Ž���� �������� ��� NULL ��ȯ
}

TreeNode* new_node(element item)
{
	TreeNode* temp = (TreeNode*)malloc(sizeof(TreeNode));
	temp->key = item;
	temp->left = temp->right = NULL;
	return temp;
}

TreeNode* insert_node(TreeNode* node, element key)
{

	// Ʈ���� �����̸� ���ο� ��带 ��ȯ�Ѵ�.
	if (node == NULL) return new_node(key);

	// �׷��� ������ ��ȯ������ Ʈ���� ��������.
	if (compare(key, node->key) < 0)
		node->left = insert_node(node->left, key);
	else if (compare(key, node->key) > 0)
		node->right = insert_node(node->right, key);
	// ��Ʈ �����͸� ��ȯ�Ѵ�.
	else if (compare(key, node->key) == 0) {
		ListNode* p = node->key.meanlist;
		p = insert_last(node->key.meanlist, key.meanlist->meaning);
	}

	return node;
}

TreeNode* min_value_node(TreeNode* node)
{
	TreeNode* current = node;
	// �� ���� �ܸ� ��带 ã���� ������
	while (current->left != NULL)
		current = current->left;
	return current;
}

TreeNode* delete_node(TreeNode* root, element key)
{
	if (root == NULL) return root;
	// ���� Ű�� ��Ʈ���� ������ ���� ����Ʈ���� �ִ� ����
	if (compare(key, root->key) < 0)
		root->left = delete_node(root->left, key);
	// ���� Ű�� ��Ʈ���� ũ�� ������ ���� Ʈ���� �ִ� ����
	else if (compare(key, root->key) > 0)
		root->right = delete_node(root->right, key);
	// Ű�� ��Ʈ�� ������ �� ��带 �����ϸ� ��
	else {
		// ù ��°�� �� ��° ���
		if (root->left == NULL) {
			TreeNode* temp = root->right;
			free(root);
			return temp;
		}
		else if (root->right == NULL) {
			TreeNode* temp = root->left;
			free(root);
			return temp;
		}
		// �� ���� ���
		TreeNode* temp = min_value_node(root->right);

		// ���� ��ȸ�� �İ� ��带 �����Ѵ�.
		root->key = temp->key;
		// ���� ��ȸ�� �İ� ��带 �����Ѵ�.
		root->right = delete_node(root->right, temp->key);
	}
	return root;
}

// �ǹ� ����Ʈ��� ����(��ȸ)
void ListAllFree(ListNode* node) {
	if (node == NULL);
	else {
		ListAllFree(node->link);
		free(node->meaning);   // meaning ����
		free(node);  // ���Ḯ��Ʈ ��� ����
	}
}

void tree_free(TreeNode* root) {
	if (root) {
		tree_free(root->left);
		tree_free(root->right);
		ListAllFree(root->key.meanlist);
		free(root->key.word);
		free(root);
	}
}


int main(void)
{
	FILE* fp;
	char command, word[20], meaning[20];
	//int node_count;
	ListNode* list_tmp;
	element e;
	TreeNode* root = NULL;
	TreeNode* tmp;

	fp = fopen("data.txt", "r");

	if (fp == NULL) {
		printf("file not found\n");
		return 0;
	}


	while (!feof(fp)) {
		fscanf(fp, "%c", &command);
		switch (command) {

		case'i':
			fscanf(fp, "%s %s ", word, meaning);
			e.meanlist = (ListNode*)malloc(sizeof(ListNode));
			e.word = (char*)malloc(strlen(word) + 1);
			e.meanlist->meaning = (char*)malloc(strlen(meaning) + 1);
			strcpy(e.word, word);
			strcpy(e.meanlist->meaning, meaning);
			e.meanlist->link = NULL;
			root = insert_node(root, e);
			printf("i\n�ܾ� : %s\n�ǹ� : %s\n\n", e.word, e.meanlist->meaning);
			break;

		case'd':
			printf("d\n");
			fscanf(fp, "%s", e);
			e.word = (char*)malloc(strlen(word) + 1);
			strcpy(e.word, word);
			printf("�ܾ� : %s\n\n", e.word);
			root = delete_node(root, e);
			break;

		case'p':
			printf("p\n");
			display(root, get_node_count(root));
			printf("\n");
			break;

		case's':
			fscanf(fp, "%s ", word);
			e.word = (char*)malloc(strlen(word) + 1);
			strcpy(e.word, word);
			tmp = search(root, e);
			if (tmp != NULL) {
				list_tmp = tmp->key.meanlist;
				printf("s\n�ܾ� : %s\n�ǹ� : ", tmp->key.word);
				printlist(list_tmp, 2);
				printf("\n\n");
			}
			break;
		}
		if (command == 'q') {
			printf("\nq\n");
			tree_free(root);
			break;
		}
	}
	fclose(fp);
	return 0;
}