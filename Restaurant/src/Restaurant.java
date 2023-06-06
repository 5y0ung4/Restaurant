
public class Restaurant {
	private Table[] tables;
	private Menu[] menus;
	
	//총 생성된 테이블과 메뉴의 개수를 저장하는 int 변수
	private int constructedTable = 0;
	private int constructedMenu = 0;
	
	void setTable(int tNum) {
		this.tables = new Table[tNum];
	}
	void setMenu(int mNum) {
		this.menus = new Menu[mNum];
	}
	
	//테이블 추가 함수. 
	void addTable (Table table) throws RestaurantException  {
		if(searchId(table) == -1 || searchId(table) == 0) {
			int roomNum = constructedTable;//constructedTable 변수에 저장된 값은 는 테이블 인덱스 + 1이므로, 다음 테이블을 constructedTable 인덱스에 저장한다.

			tables[roomNum] = table;
			
			constructedTable++;
		}else { //테이블 id가 중복이면 예외를 던짐
			throw new RestaurantException("id중복", table.getId());
		}
		
		
	}
	
	void deleteTable(Table table) throws RestaurantException { //테이블 삭제 메서드
		if(searchId(table) == -1) {
			throw new RestaurantException("id없음", table.getId());
		}
		int i = 0;
		for(Table t : tables) {
			if(t != null && table.getId().equals(t.getId())) {//삭제할 테이블을 찾았다면
				for(int j = i; j < constructedTable; j++) {//뒤의 인덱스를 한칸씩 당겨옴으로써 배열 안정화
					tables[j] = tables[j + 1];
				}
				tables[constructedTable - 1] = null;//마지막 인덱스 제거
				constructedTable--;//테이블 총 개수 -1
			}
			i++;
		}
	}
	
	void addMenu(Menu menu)throws RestaurantException  {
		if(searchMenu(menu) != -1) {
			throw new RestaurantException("menu중복", menu.getName());
		}
		int menuNum = constructedMenu;
		
		menus[menuNum] = menu;
		constructedMenu++;
	}
	
	void deleteMenu(Menu menu)throws RestaurantException {
		if(searchMenu(menu) == -1) {
			throw new RestaurantException("menu없음", menu.getName());
		}
		int i = 0;
		for(Menu m : menus) {
			if(m != null && menu.getName().equals(m.getName())) {
				for(int j = i; j < constructedMenu; j++) {
					menus[j] = menus[j + 1];
				}
				menus[constructedMenu - 1] = null;
				constructedMenu--;
			}
			i++;
		}
}
	
	Table[] getTables() {
		return tables;
	}
	
	Menu[] getMenus() {
		return menus;
	}
	
	int searchId(Table table) {
		int i = 0;
		if (tables == null) {
			return 0;
		}
		for(Table t : this.tables) {
			if(t != null && table.getId().equals(t.getId())) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	int searchMenu(Menu menu) {
		int i = 0;
		if (menus == null) {
			return 0;
		}
		for(Menu m : this.menus) {
			if(m != null && menu.getName().equals(m.getName())) {
				return i;
			}
			i++;
		}
		return -1;
	}
}
