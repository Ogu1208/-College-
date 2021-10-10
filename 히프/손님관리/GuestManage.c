#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#define MAX_ELEMENT 200

typedef struct {
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

int compare(element e1, element e2) {
	return strcmp(e1.name, e2.name);
}

//현재 요소의 개수가 heap_size인 히프 h에 item을 삽입한다.
//삽입 함수 손님관리 히프는 최소히프이다!!
void insert_min_heap(HeapType* h, element item)
{
	int i;
	i = ++(h->heap_size);

	//트리를 거슬러 올라가면서 부모 노드와 비교하는 과정
	while ((i != 1) && (compare(item, h->heap[i / 2]) < 0)) {
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}
	h->heap[i] = item;  // 새로운 노드를 삽입
}




// 삭제 함수
element delete_min_heap(HeapType* h)
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
			(compare(h->heap[child], h->heap[child+1]) > 0))
			child++;
		
		if (compare(temp, h->heap[child]) <= 0) break;
		// 한단계 아래로 이동
		h->heap[parent] = h->heap[child];
		parent = child;
		child *= 2;
	}

	h->heap[parent] = temp;
	return item;
}

int decideHeapSize(int n) {
	int i = 0;

	while (1) {
		if (pow(2, i) > n)
			return pow(2, i);
		i++;
	}

}

void printHeap(HeapType* h) {
	int i;
	printf("< 히프출력 >\n");
	for (i = 1; i <= h->heap_size; i++) {
		printf("%d: %s => ", i, h->heap[i].name);
	}
	printf("\n");
}

int main(void)
{
	int i, hsize, count = 0;
	char ntemp[20], ch;
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

	while (!feof(fp)) {
		fscanf(fp, "%c", &ch);
		if (ch == 'i') {
			fscanf(fp, "%s", ntemp);
			count++;
		}
			
	}

	hsize = decideHeapSize(count);
	printf("hsize = %d\n\n", hsize);
	heap->heap = (element*)malloc(sizeof(element) * hsize);

	rewind(fp);

	while (!feof(fp)) {
		fscanf(fp, "%c", &ch);

		switch (ch) {

		case'i':
			fscanf(fp, "%s", ntemp);
			e.name = (char*)malloc(sizeof(char) * (strlen(ntemp) + 1));
			strcpy(e.name, ntemp);
			insert_min_heap(heap, e);
			printf(">> 손님(%s) 입장\n", e.name);
			printHeap(heap);
			printf("\n");
			break;

		case'o':
			e = delete_min_heap(heap);
			printf(">>손님(%s) 퇴장\n", e.name);
			printHeap(heap);
			printf("\n");
			break;
		}

	}

	printf("안녕\n");
	printf("%s", heap->heap[1].name);

	free(heap);
	return 0;
}

