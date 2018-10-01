

public class ArraySet implements Set {
	private Object[] objects = new Object[1000];
	private int size=0;
	private int i;

	@Override
	public boolean add(Object object) {        //�������߰��޼ҵ�
		// TODO Auto-generated method stub
		if(contains(object)) {                // �ߺ�����   
			return false;
		}else {
			objects[size++]= object;          // �������߰��� �� ������ 1����
			return true;
		}
	}

	@Override
	public boolean contains(Object object) {    //�ߺ������ǰ� �޼ҵ�
		// TODO Auto-generated method stub
		for(int i=0; i<size; i++) {
			if(objects[i].equals(object)) return true;     
		}
		return false;
	}



	@Override
	public Object getNext() {                    //���� �����Ͱ� ��ȯ
		// TODO Auto-generated method stub
		return objects[i++];
	}

	@Override
	public boolean remove(Object object) {             //���Ÿ޼ҵ�
		// TODO Auto-generated method stub
		for(int i=0; i<size; i++) {
			if(objects[i].equals(object)) {
				System.arraycopy(objects, i+1, objects, i , size-i-1);       //������ �����͸� �����鼭 ���ÿ� �迭�� ����������
				objects[size-1]= null;                            //������ �� �������� ���� ������ null������
				size--;
				System.out.println("�����Ǿ����ϴ�");
				return true;
			}
		}
		System.out.println("���� �������Դϴ�");
		return false;
	}

	@Override
	public int size() {                           //size�� ��ȥ �޼ҵ�
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object getFrist() {                        //objects[0] �� ��ȯ�޼ҵ�
		// TODO Auto-generated method stub
		i=0;                                          //i�� 0�����ʱ�ȭ������
		return objects[i++];                         
	} 
}
