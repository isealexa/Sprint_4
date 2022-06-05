public class Account {

    private final String name;

    public Account(String name) {
        this.name = name;
    }

    public boolean checkNameToEmboss() {
        /*
             Этот метод должен проверять, что сохранённая через конструктор строка соответствует требованиям.
             Если строка удовлетворяет условиям, метод возвращает true, иначе — false.
         */
        if (name == null) {
            return false;
        }

        // я понимаю, это лишняя проверка так как ниже есть проверка на подсчет пробелов.
        // Стоит убрать или оставить?
        if (name.isEmpty()) {
            return false;
        }

        if (name.length() < 3 || name.length() > 19) {
            return false;
        }

        // тут тоже я понимаю, что contains - это лишнее как раз из-за того, что ниже есть проверка на подсчет пробелов.
        // Стоит убрать contains из if-а или оставить?
        if (!name.contains(" ") || name.startsWith(" ") || name.endsWith(" ")) {
            return false;
        }

        //подсчёт количества пробелов
        int count = 0;
        for (char c : name.toCharArray()) {
            if (c == ' ') {
                count++;
            }
        }
        if (count != 1) { //по ТЗ в строке должен быть только один пробел
            return false;
        }

        //Проверка на то, чтобы в строке могли содержаться пробел и только буквы: латиница или кирилица
        if (!name.matches("[a-zA-Z\u0430-\u044F\u0410-\u042F ]+")) {
            return false;
        }

        return true;
    }
}
