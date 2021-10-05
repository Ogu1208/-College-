/*
	작성일 : 2021.10.04
	작성자 : 김민아
	프로그램명 : 파일에서 자료를 읽어 오름차순으로 정렬하는 프로그램
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <memory.h>

int count = 0;

// 의미 연결리스트
typedef struct ListNode {
	char* meaning;
	struct ListNode* link;
}ListNode;

//데이터 형식
typedef struct element {
	char* word; // 키필드
	ListNode *meanlist;
}element;

typedef struct TreeNode {
	element key;
	struct TreeNode* left, *right;
}TreeNode;

// 만약 e1 < e2 이면 -1 반환
// 만약 e1 == e2 이면 0 반환
// 만약 e1 > e2 이면 1 반환
int compare(element e1, element e2)
{
	return strcmp(e1.word, e2.word);
}

// 트리 노드의 개수를 세는 함수
int get_node_count(TreeNode* node)
{
	int count = 0;

	if (node != NULL)
		count = 1 + get_node_count(node->left) +
		get_node_count(node->right);

	return count;
}

// 트리노드의 의미 연결리스트의 마지막에 삽입
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


	//헤드포인터가 NUll이면(즉 리스트가 비어있을 경우)
	if (head == NULL) {
		head = p;
	}

	//그렇지 않으면
	else {
		ListNode* temp = head;

		while (temp->link != NULL)
			temp = temp->link;

		temp->link = p;
	}

	return head;
}

// 트리노드의 의미 연결리스트 출력
void printlist(ListNode* p, int c)
{
	ListNode* tmp = p;
	// c == 1이면 트리의 마지막 노드가 아닌 경우
	if (c == 1) {
		while (tmp != NULL) {
			//연결리스트의 마지막 노드 쉼표 제거
			printf("%s, ", tmp->meaning);
			tmp = tmp->link;
		}
	}
	else {  // 트리의 마지막 노드라면
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

//이진 탐색 트리 출력 함수 (중위순회)
void display(TreeNode* p, int node)
{
	if (p != NULL) {
		ListNode* tmp = p->key.meanlist;
		display(p->left, node);
		printf("%s ", p->key.word);
		/**/
		if (count != node - 1) { // 마지막 노드가 아니면
			count++;
			printlist(tmp, 1);
		}
		else { // 마지막 노드이면(마지막 의미 쉼표제거)
			count++;
			printlist(tmp, 2);
		}

		display(p->right, node);
	}
}

// 이진 탐색 트리 탐색 함수
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
	return p;  // 탐색에 실패했을 경우 NULL 반환
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

	// 트리가 공백이면 새로운 노드를 반환한다.
	if (node == NULL) return new_node(key);

	// 그렇지 않으면 순환적으로 트리를 내려간다.
	if (compare(key, node->key) < 0)
		node->left = insert_node(node->left, key);
	else if (compare(key, node->key) > 0)
		node->right = insert_node(node->right, key);
	// 루트 포인터를 반환한다.
	else if (compare(key, node->key) == 0) {
		ListNode* p = node->key.meanlist;
		p = insert_last(node->key.meanlist, key.meanlist->meaning);
	}

	return node;
}

TreeNode* min_value_node(TreeNode* node)
{
	TreeNode* current = node;
	// 맨 왼쪽 단말 노드를 찾으러 내려감
	while (current->left != NULL)
		current = current->left;
	return current;
}

TreeNode* delete_node(TreeNode* root, element key)
{
	if (root == NULL) return root;
	// 만약 키가 루트보다 작으면 왼쪽 서브트리에 있는 것임
	if (compare(key, root->key) < 0)
		root->left = delete_node(root->left, key);
	// 만약 키가 루트보다 크면 오른쪽 서브 트리에 있는 것임
	else if (compare(key, root->key) > 0)
		root->right = delete_node(root->right, key);
	// 키가 루트와 같으면 이 노드를 삭제하면 됨
	else {
		// 첫 번째나 두 번째 경우
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
		// 세 번쨰 경우
		TreeNode* temp = min_value_node(root->right);

		// 중위 순회시 후계 노드를 복사한다.
		root->key = temp->key;
		// 중위 순회시 후계 노드를 삭제한다.
		root->right = delete_node(root->right, temp->key);
	}
	return root;
}

// 의미 리스트노드 삭제(순회)
void ListAllFree(ListNode* node) {
	if (node == NULL);
	else {
		ListAllFree(node->link);
		free(node->meaning);   // meaning 해제
		free(node);  // 연결리스트 노드 해제
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
			printf("i\n단어 : %s\n의미 : %s\n\n", e.word, e.meanlist->meaning);
			break;

		case'd':
			printf("d\n");
			fscanf(fp, "%s", e);
			e.word = (char*)malloc(strlen(word) + 1);
			strcpy(e.word, word);
			printf("단어 : %s\n\n", e.word);
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
				printf("s\n단어 : %s\n의미 : ", tmp->key.word);
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