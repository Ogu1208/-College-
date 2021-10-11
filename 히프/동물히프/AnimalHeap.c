/*
	�ۼ��� : 2021.10.10
	�ۼ��� : ��ξ�
	���α׷��� : �켱����ť�� �̿��� �������� ���α׷�
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

// ���� �Լ�
HeapType* create()
{
	return (HeapType*)malloc(sizeof(HeapType));
}

// �ʱ�ȭ �Լ�
void init(HeapType* h)
{
	h->heap_size = 0;
}

//���� ����� ������ heap_size�� ���� h�� item�� �����Ѵ�.
//���� �Լ�
void insert_max_heap(HeapType* h, element item)
{
	int i;
	i = ++(h->heap_size);

	//Ʈ���� �Ž��� �ö󰡸鼭 �θ� ���� ���ϴ� ����
	while ((i != 1) && (item.key > h->heap[i / 2].key)) {
		h->heap[i] = h->heap[i / 2];
		i /= 2;
	}
	h->heap[i] = item;  // ���ο� ��带 ����
}

// ���� �Լ�
element delete_max_heap(HeapType* h)
{
	int parent, child;
	element item, temp;

	item = h->heap[1];
	temp = h->heap[(h->heap_size)--];
	parent = 1;
	child = 2;

	while (child <= h->heap_size) {
		// ���� ����� �ڽĳ�� �� �� ū �ڽĳ�带 ã�´�.
		if ((child < h->heap_size) &&
			(h->heap[child].key) < h->heap[child + 1].key)
			child++;
		if (temp.key >= h->heap[child].key) break;
		// �Ѵܰ� �Ʒ��� �̵�
		h->heap[parent] = h->heap[child];
		parent = child;
		child *= 2;
	}

	h->heap[parent] = temp;
	return item;
}

// ���� ����� ���ϴ� �Լ�
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

	// ���� ������ ���ϱ�
	while (!feof(fp)) {
		fscanf(fp, "%d%s", &e.key, ntemp);
		count++;
	}

	hsize = decideHeapSize(count);
	printf("hsize = %d\n\n", hsize);
	heap->heap = (element*)malloc(sizeof(element) * hsize);

	rewind(fp);

	// �����͸� �о� ������ ����
	while (!feof(fp)) {
		fscanf(fp, "%d%s", &e.key, ntemp);
		e.name = (char*)malloc(sizeof(char) * (strlen(ntemp) + 1));
		strcpy(e.name, ntemp);
		insert_max_heap(heap, e);
		count++;
		printf(">> (%d : %s) �Է�\n", e.key, e.name);
	}

	printf("\n===== ���� ���� ��� =====\n\n");
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

