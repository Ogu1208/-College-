/*
	작성일 : 2021.10.10
	작성자 : 김민아
	프로그램명 : 우선순위큐를 이용한 동물히프 프로그램
*/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#define MAX_ELEMENT 200

typedef struct {
	int key;
	char* name;
}element;

typedef struct {
	element* heap;
	int heap_size;
}HeapType;

// 생성 함수
HeapType* create()
{
	return (HeapType*)malloc(sizeof(HeapType));
}

// 초기화 함수
void init(HeapType* h)
{
	h->heap_size = 0;
}

//현재 요소의 개수가 heap_size인 히프 h에 item을 삽입한다.
//삽입 함수
void insert_max_heap(HeapType* h, element item)
{
	int i;
	i = ++(h->heap_size);

	//트리를 거슬러 올라가면서 부모 노드와 비교하는 과정
	while ((i != 1) && (item.key > h->heap[i / 2].key)) {
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}
	h->heap[i] = item;  // 새로운 노드를 삽입
}

// 삭제 함수
element delete_max_heap(HeapType* h)
{
	int parent, child;
	element item, temp;

	item = h->heap[1];
	temp = h->heap[(h->heap_size)--];
	parent = 1;
	child = 2;

	while (child <= h->heap_size) {
		// 현재 노드의 자식노드 중 더 큰 자식노드를 찾는다.
		if ((child < h->heap_size) &&
			(h->heap[child].key) < h->heap[child + 1].key)
			child++;
		if (temp.key >= h->heap[child].key) break;
		// 한단계 아래로 이동
		h->heap[parent] = h->heap[child];
		parent = child;
		child *= 2;
	}

	h->heap[parent] = temp;
	return item;
}

// 히프 사이즈를 구하는 함수
int decideHeapSize(int n) {
	int i = 0;

	while (1) {
		if (pow(2, i) >= n)
			return pow(2, i);
		i++;
	}
}

int main(void)
{
	int i, hsize, count = 0;
	char ntemp[20];
	HeapType* heap;
	element e;
	FILE* fp;

	heap = create();
	init(heap);
	fp = fopen("data.txt", "r");

	if (fp == NULL) {
		printf("file not found\n");
		return 0;
	}

	// 히프 사이즈 구하기
	while (!feof(fp)) {
		fscanf(fp, "%d%s", &e.key, ntemp);
		count++;
	}

	hsize = decideHeapSize(count);
	printf("hsize = %d\n\n", hsize);
	heap->heap = (element*)malloc(sizeof(element) * hsize);

	rewind(fp);

	// 데이터를 읽어 히프에 삽입
	while (!feof(fp)) {
		fscanf(fp, "%d%s", &e.key, ntemp);
		e.name = (char*)malloc(sizeof(char) * (strlen(ntemp) + 1));
		strcpy(e.name, ntemp);
		insert_max_heap(heap, e);
		count++;
		printf(">> (%d : %s) 입력\n", e.key, e.name);
	}

	printf("\n===== 동물 히프 출력 =====\n\n");
	//printf("(heap_size : %d)\n\n", heap->heap_size);

	for (i = 1; i <= heap->heap_size; i++) {
		printf("%d:%s => ", heap->heap[i].key, heap->heap[i].name);
	}

	for (i = 1; i <= heap->heap_size; i++) {
		free(heap[i].heap->name);
	}

	free(heap);
	return 0;
}

