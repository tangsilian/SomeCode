package matches;

import java.io.IOException;
import java.util.Scanner;

public class systemin {

	/**
	 * Scannar
	 */
	public static void main(String[] args) {
		System.out.println("�����룺");
		Scanner sc = new Scanner(System.in);
		System.out.println(sc.nextLine());
	}

	/**
	 * ��ȡ����int
	 * 
	 * @return
	 */
	public int ReturnInt() {
		int i = 0;
		try {
			// ��ȡ���ص���int���͵�
			i = System.in.read();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// ǿ��ת��
		System.out.println((char) i);
		return i;
	}

	/**
	 * ��������Ϊ����
	 * 
	 * @throws IOException
	 */
	public void byteBuffer() throws IOException {
		// ��дһ������ռ�
		byte[] bytebuffer = new byte[1024];

		// ��ȡ����

		int n = System.in.read(bytebuffer);

		// int to String
		String string = new String(bytebuffer, 0, n);

		System.out.println(string);
	}

}
