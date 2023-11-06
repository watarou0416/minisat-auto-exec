package excel;

public class hennkan {
    public static void main(String[] args) {
        String stringValue = cell.toString();

        try {
            // String型からint型に変換
            int intValue = Integer.parseInt(stringValue);
            System.out.println("変換後の値: " + intValue);
        } catch (NumberFormatException e) {
            // エラーハンドリング: 数字でない文字列が入力された場合の対応
            System.out.println("エラー: 数字でない文字列が入力されました");
        }
    }
}
