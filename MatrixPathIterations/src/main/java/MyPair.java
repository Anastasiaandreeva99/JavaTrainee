import java.util.Queue;

public class MyPair {
		private Integer i;
		private Integer j;
		private  MyPair parent;
	

		public MyPair(Integer i, Integer j, MyPair parent) {
			super();
			this.i = i;
			this.j = j;
			this.parent = parent;
		}
		

		public MyPair getParent() {
			return parent;
		}


		public void setParent(MyPair parent) {
			this.parent = parent;
		}


		public Integer getI() {
			return i;
		}

		public void setI(Integer i) {
			this.i = i;
		}

		public Integer getJ() {
			return j;
		}

		public void setJ(Integer j) {
			this.j = j;
		}

	}

