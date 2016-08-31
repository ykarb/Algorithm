public class Main {

    public static void main(String[] args) {
        YaserHashLinkedList yaserHash = new YaserHashLinkedList(10);
        yaserHash.put("Yaser",90);
        yaserHash.put("Yaser",9088);
        yaserHash.put("Yasor",900);
        yaserHash.put("Forroz",100);

        System.out.println(yaserHash.getValue("Yaser"));
    }
}