
public class HashTable {
	int key;
	int h[] = new int[97];
	int size = 97;
	int size2 = 59;
	int i;
	int collision_count = 0;
	final int DELETED = -1;
	
	//�������� ����
	public void linearInsert(int x) {
		key = x % size;
		while(h[key] != 0) {
			key = (key + 1) % size; //�ؽ�
			collision_count++; //�浹Ƚ��
		}
		h[key] = x;
	}
	
	//���������� ����
	public void quadraticInsert(int x) {
		key = x % size;
		i=0;
		while(h[key] != 0) {
			i++;
			key = ((x % size) + i*i) % size;  //�ؽ�
			collision_count++;
		}
		h[key] = x;
	}
	
	//���� �ؽ� ����
	public void doubleInsert(int x) {
		key = x % size;
		i = 0;
		while(h[key] != 0) {
			i++;
			key = ((x % size) + i * (x % size2)+1) % size; //�ؽ�
			collision_count++;
		}
	
		h[key] = x;
	}
	
	
	//�˻��� �� ã���� �ش� ���� �ε���(Ű) ��ȯ, ��ã���� 0��ȯ
	public int linearSearch(int x) {
		key = x % size;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x) {
				return key;
			}
			else {
				key = (key + 1) % size;
				collision_count++;
			}
		}
		return 0;
	}
	
	public int quadraticSearch(int x) {
		key = x % size;
		i=0;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x ) { 
				return key;
			}
			else {
				i++;
				key = ((x % size) + i*i) % size;
			}
		}
		return 0;
	}
	
	public int doubleSearch(int x) {
		key = x % size;
		i = 0;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x) {
				return key;
			}
			else {
				i++;
				key = ((x % size) + i * (x % size2)+1) % size;
			}
		}
		return 0;
	}
	
	
	//����
	public void linearDelete(int x) {
		key = x % size;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x) {
				h[key] = DELETED;
			}
			else {
				key = ((key + 1) % size);
			}
		}
	}
	
	public void quadraticDelete(int x) {
		key = x % size;
		i =0;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x) {
				h[key] = DELETED;
			}
			else {
				i++;
				key = ((x % size) + i*i) % size; 
			}
		}
	}
	
	public void  doubleDelete(int x) {
		key = x % size;
		i = 0;
		while(h[key] != 0 || h[key] == DELETED) {
			if(h[key] == x) {
				h[key] = DELETED;
			}
			else {
				i++;
				key = ((x % size) + i * (x % size2)+1) % size;
			}
		}
	}
	
	public int getCollison() {
		return collision_count;
	}
}
