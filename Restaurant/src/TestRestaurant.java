import java.util.Scanner;

public class TestRestaurant {
	public static void main(String args[]) {
		Restaurant china = new Restaurant();
		Scanner input = new Scanner(System.in);
		int tableArr = 0, menuArr = 0;
		String tName, mName, oName;
		int tNum, mNum, mPrice, oNum, pay;
		
		while(true) {
			System.out.println("하려는 작업은 무엇입니까?");
			System.out.println("0. 종료");
			System.out.println("1. 테이블 생성");
			System.out.println("2. 테이블 삭제");
			System.out.println("3. 메뉴 추가");
			System.out.println("4. 메뉴 삭제");
			System.out.println("5. 메뉴 주문");
			System.out.println("6. 체크 인");
			System.out.println("7. 체크 아웃");
			System.out.println("하려는 작업의 번호를 입력하세요.");
			
			int work = input.nextInt();
			input.nextLine();
			
			

			try {
				Table tables[] = china.getTables();
			switch (work) {
				case 0:
					break;
				case 1:
					if(tableArr == 0) {
						System.out.println("우선, 몇 개의 테이블을 생성할지 정해야 합니다. 개수를 입력하세요. :");
						int tableTotal = input.nextInt();
						china.setTable(tableTotal);
						input.nextLine();
						tableArr++;
					}
					System.out.println("추가하려는 테이블의 이름은 무엇입니까? : ");
					tName = input.next();
					input.nextLine();
					System.out.println("추가하려는 테이블의 최대 인원은 몇 명입니까? : ");
					tNum = input.nextInt();
					input.nextLine();
					Table table = new Table(tName, tNum, false);
					china.addTable(table);
					break;
				case 2:
					System.out.println("삭제하려는 테이블의 이름은 무엇입니까? : ");
					tName = input.next();
					input.nextLine();
					china.deleteTable(new Table(tName));
					break;
				case 3:
					if(menuArr == 0) {
						System.out.println("우선, 몇 개의 메뉴를 생성할지 정해야 합니다. 개수를 입력하세요. :");
						int menuTotal = input.nextInt();
						china.setMenu(menuTotal);
						input.nextLine();
						menuArr++;
					}
					System.out.println("추가하려는 메뉴의 이름은 무엇입니까? : ");
					mName = input.next();
					input.nextLine();
					System.out.println("추가하려는 메뉴의 가격은 얼마입니까? : ");
					mPrice = input.nextInt();
					input.nextLine();
					System.out.println("추가하려는 메뉴는 몇 개입니까? : ");
					mNum = input.nextInt();
					input.nextLine();
					Menu menu = new Menu(mName, mPrice, mNum);
					china.addMenu(menu);
					break;
				case 4:
					System.out.println("삭제하려는 메뉴의 이름은 무엇입니까? : ");
					mName = input.next();
					input.nextLine();
					china.deleteMenu(new Menu(mName));
					break;
				case 5:
					System.out.println("어떤 테이블에 메뉴를 주문하시겠습니까? : ");
					tName = input.next();
					input.nextLine();
					Table t = new Table(tName);
					
					Table tTable = tables[china.searchId(t)];
					System.out.println("어떤 메뉴를 주문하시겠습니까? : ");
					oName = input.next();
					input.nextLine();
					System.out.println("몇 개 주문하시겠습니까? : ");
					oNum = input.nextInt();
					input.nextLine();
					tTable.addOrder(new Order(oName, oNum, china));
					break;
				case 6:
					System.out.println("어떤 테이블에 체크인하시겠습니까? : ");
					tName = input.next();
					input.nextLine();
					tables[china.searchId(new Table(tName))].setAvailable(true);
					break;
				case 7:
					System.out.println("체크 아웃 하려는 테이블의 이름을 입력하세요. : ");
					tName = input.next();
					input.nextLine();
					pay = tables[china.searchId(new Table(tName))].checkOut();
					System.out.println("계산하실 금액은 " + pay + "원 입니다.");
				}
				}catch (RestaurantException e) {
					handleException(e);
				}catch (Exception e) {
					System.out.println("예상치 못한 오류가 발생했습니다. 다시 시도하십시오.");
				}
				finally {
					if(work == 0)
						break;
						
				}
			}
			Table[] tables = china.getTables();
			if(tables == null) {
				System.out.println("생성된 테이블이 없습니다.");
			}else {
				for(int i = 0; i < tables.length; i++)
					if(tables[i] == null) {
						break;
					}else {
						System.out.println(tables[i].getId() + " 방, 최대 " + tables[i].getCapacity() + "명, 상태: " + tables[i].getAvailable());	
					}
			}
				
			Menu[] menus = china.getMenus();
			if(menus == null) {
				System.out.println("생성된 메뉴가 없습니다.");
			}else {
				for(int i = 0;  i < menus.length ; i++)
					if(menus[i] == null) {
						break;
					} else {
						System.out.println("메뉴 " + (i+1) + ": " + menus[i].getName() + ", 가격: " + menus[i].getPrice() + ", 재고: " + menus[i].getStuck());
					}
			}
		
		
	
	}
	
	//예외처리의 유지보수성을 높이기 위해 예외처리 함수를 생성
	private static void handleException(RestaurantException e) {
		String text = e.getMessage();
		String extra = e.getValue();
		if(text.equals("id중복")) {
			System.out.println("추가하려는 테이블의 id가 존재합니다. 같은 id를 두 개 추가할 수 없습니다. : " + extra);
		}else if(text.equals("id없음")) {
			System.out.println("삭제하려는 id의 테이블이 없습니다. : " + extra);
		}else if(text.equals("menu중복")) {
			System.out.println("추가하려는 메뉴가 이미 존재합니다. 같은 메뉴를 두 개 추가할 수 없습니다. : " + extra);
		}else if(text.equals("menu없음")) {
			System.out.println("삭제하려는 메뉴가 없습니다. : " + extra);
		}else if(text.equals("수량없음")) {
			System.out.println("주문하려는 메뉴의 재고가 부족합니다.");
		}
		
	}
}
