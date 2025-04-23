package veniamin.backend.atelie.exception.errors;

public enum BadRequestError {


    NOT_CORRECT_PASSWORD("Неверный пароль"),
    ORDER_ALREADY_EXIST("Заказ уже был создан"),
    NOT_CORRECT_OLD_PASSWORD("Неверный старый пароль для изменения данных"),
    USER_ALREADY_BOUGHT_COURSE("Пользователь уже обладает данным курсом"),
    USER_NOT_BOUGHT_COURSE("Пользователем не куплен данный курс"),
    ANSWER_NOT_BELONG_TO_THIS_QUESTION("Ответ относится к другому вопросу"),
    NOT_CORRECT_REQUEST_BODY_DATA("В теле запроса невалидные данные"),
    NOT_CORRECT_REFRESH_TOKEN("Неверный рефреш токен"),
    USER_ALREADY_VERIFICATED("Пользователь уже был верифицирован"),
    USER_NOT_VERIFICATED("Пользователь не верифицирован"),
    NOT_CORRECT_VERIFICATION_CODE("Код верификации не корректен или не был запрошен"),
    USER_ALREADY_EXISTS("Пользователь с такой почтой уже существует"),
    COURATOR_DOESNT_TEACH_ON_THAT_COURSE("Данный куратор не курирует этот курс"),
    COURATOR_ALREADY_TEACH_ON_THAT_COURSE("Куратор уже курирует данный курс"),
    USER_IS_NOT_A_CURATOR("Пользователь не является куратором"),
    USER_CAN_MAKE_ORDER_ONLY_FOR_HISSELF("Пользователь может создавать заказы только для себя"),
    USER_CAN_GET_ONLY_HIS_ORDERS("Пользователь может получать только свои заказы"),
    FILE_FOR_AVATAR_MUST_BE_IMAGE("Файл для аватарки должен быть изображением"),
    NOT_ENOUGH_QUESTIONS("Недостаточно вопросов в тесте"),
    QUESTION_ANSWER_ALREADY_EXISTS("Ответ пользователя на данный вопрос уже существует"),
    PROMOCODE_ALREADY_EXISTS("Промокод с таким хешем существует"),
    NO_TEST_PASSED("Нет пройденных тестов в теме"),
    TEST_UNAVAILABLE("Тест недоступен"),
    THEME_TEST_UNAVAILABLE("Тест темы недоступен, так как есть непройденные уроки"),
    TESTS_IN_PREVIOUS_LESSONS_NOT_PASSED("Тест недоступен, так как есть непройденные тесты в предыдущих уроках темы"),
    TEST_NOT_ATTACHED_TO_COURSE("Тест недоступен, так как он не прикреплен к курсу"),
    TEST_NOT_ATTACHED_TO_THEME_OR_LESSON("Тест недоступен, так как он не прикреплен к теме или к уроку"),
    TEST_RESULT_UNAVAILABLE("Результат теста недоступен"),
    TEST_RESULTS_UNAVAILABLE_BECAUSE_TEST_NOT_PASSED("Результаты прошлых попыток нельзя получить, пока тест не будет сдан"),
    QUESTION_ANSWER_UNAVAILABLE("Ответ на вопрос недоступен"),
    CHAT_UNAVAILABLE("Чат недоступен"),
    CHAT_IS_DELETED("Чат удален"),
    CHAT_NOT_FOUND("Чат не найден"),
    CAN_NOT_CREATE_CHAT_WITHOUT_CURRENT_USER("Невозможно создать чат, среди участников которого нет текущего пользователя"),
    USER_IS_NOT_MEMBER_OF_THIS_CHAT("Невозможно удалить данный чат, так как пользователь не является его участником"),
    USER_IS_NOT_MESSAGE_OWNER("Невозможно удалить сообщение, так как пользователь не является его автором"),
    NOT_CORRECT_CHAT_ID("Неверный id чата"),
    CHAT_ALREADY_EXIST("Чат уже существует"),
    HAVE_NO_PASSED_TEST_FOR_COURSE("Невозможно завершить курс, так как есть незавершенные тесты/уроки"),
    NOT_CORRECT_WEBSOCKET_DESTINATION("Неверный адрес подписки веб-сокета"),
    NOT_CORRECT_FILE_EXTENSION_FOR_THIS_FILE_TYPE("Неверное расширения для данного типа файлов"),
    FILE_CAN_NOT_BE_DELETED("Файл не может быть удален"),
    FILE_DELETED("Файл удален"),
    NO_LESSON_FOR_THEME_BEFORE_PUBLISH_COURSE("Невозможно опубликовать курс, так как есть темы без уроков"),
    NO_THEME_FOR_COURSE_BEFORE_PUBLISH_COURSE("Невозможно опубликовать курс, пока в него не будут добавлены темы"),
    NO_TEST_FOR_THEME_BEFORE_PUBLISH_COURSE("Невозможно опубликовать курс, пока в темах не будут установлены тесты"),
    CAN_NOT_ADD_NOT_PUBLISHED_COURSE_TO_ORDER("Невозможно создать заказ, так как среди добавленных курсов есть неопубликованные"),
    CAN_NOT_ADD_NOT_ACTIVE_COURSE_TO_ORDER("Невозможно создать заказ, так как среди добавленных курсов есть неактивные"),
    CAN_NOT_ADD_DELETED_COURSE_TO_ORDER("Невозможно создать заказ, так как среди добавленных курсов есть удаленные"),
    COURSE_ALREADY_HAS_THIS_THEME("У курса уже есть данная тема"),
    GAPS_IN_THEMES_NUMBERS_SEQUENCE("Невозможно опубликовать курс, в последовательности номеров тем есть пробелы"),
    GAPS_IN_LESSON_NUMBERS_SEQUENCE("Невозможно опубликовать курс, в последовательности номеров уроков темы есть пробелы"),
    EMPTY_FIELDS_IN_LESSON("Невозможно опубликовать курс, в уроке есть незаполненные поля"),
    COUNT_OF_QUESTIONS_LOWER_OR_EQUALS_TO_ZERO("Невозможно опубликовать курс, количество выводимых вопросов в тесте меньше или равно нулю"),
    ACTUAL_COUNT_OF_QUESTION_LOWER_TO_COUNT_OF_QUESTION_FIELD("Невозможно опубликовать курс, количество вопросов в тесте меньше указанного числа"),
    COUNT_OF_ANSWERS_LOWER_OR_EQUALS_TO_ONE("Невозможно опубликовать курс, в вопросе теста должно быть больше одного ответа"),
    COUNT_OF_CORRECT_ANSWERS_NOT_EQUALS_TO_ONE("Невозможно опубликовать курс, в вопросе теста должен быть один правильный ответ"),
    COURSE_ALREADY_IN_BASKET("Курс уже в корзине"),
    COURSE_NOT_IN_BASKET("Курса нет в корзине"),
    CAN_NOT_ADD_NOT_PUBLISHED_COURSE_TO_BASKET("Нельзя добавить данный курс в корзину, так как он еще не опубликован"),
    STACK_NAME_ALREADY_EXIST("Стек с данным именим уже существует"),
    STACK_DELETED("Стек удален"),
    STACK_NOT_ACTIVE("Стек не активен"),
    FILE_MUST_BE_IMAGE("Файл должен быть изображением"),
    NO_COURATOR_ON_COURSE("Курс недоступен, так как на нем не работает ни одного куратора"),
    COURSE_EMPTY_NAME("Курс не может быть опубликован. У курса пустое имя"),
    COURSE_NO_THEME("Курс не может быть опубликован. У курса нет тем"),
    COURSE_NO_COURATOR("Курс не может быть опубликован. У курса нет куратора"),
    COURSE_NOT_ACTIVE_BEFORE_PUBLISH("Курс не может быть опубликован. Курс не активен"),
    COURSE_IS_DELETED_BEFORE_PUBLISH("Курс не может быть опубликован. Курс удален"),
    COURSE_DELETED("Курс был удален"),
    COURSE_NOT_PUBLISHED("Курс не опубликован"),
    COURSE_NOT_ACTIVE("Курс не активен"),
    THEME_NOT_ACTIVE("Тема не активна"),
    THEME_DELETE("Тема удалена"),
    THEME_AUTHOR_NOT_SETTED("У темы не назначен автор"),
    LESSON_NOT_ACTIVE("Урок не активен"),
    LESSON_DELETED("Урок удален"),
    TEST_NOT_ACTIVE("Тест не активен"),
    TEST_DELETED("Тест удален"),
    TEST_SAME_QUESTION_TEXT("У теста есть вопросы c одинаковым названием"),
    PAGE_WITH_THIS_NAME_ALREADY_EXIST("Страница с таким именем уже существует"),
    PROMOCODE_ALREADY_CAN_BE_USED_ON_THIS_COURSE("Промокод уже привязан к данном курсу"),
    CAN_NOT_CREATE_CERTIFICATE_FILE("Невозможно создать сертификат на сервере"),
    ERROR_WHILE_CREATING_PDF_CERTIFICATE("Ошибка при создании PDF сертификата"),
    REVIEW_DTO_COURSE_ID_FOR_CREATE_REVIEW_FOR_COURSE_SHOULD_NOT_BE_NULL("При создании отзыва для курса, нужно чтобы в теле запроса присуствовал айди курса"),
    REVIEW_DTO_LESSON_ID_FOR_CREATE_REVIEW_FOR_LESSON_SHOULD_NOT_BE_NULL("При создании отзыва для урока, нужно чтобы в теле запроса присуствовал айди урока"),
    USER_NOT_END_COURSE_FOR_REVIEW("Пользователь не закончил курс для того, чтобы иметь возможность оценить его"),
    LESSON_NOT_AVIABLE_FOR_USER("Урок не доступен для пользователя"),
    OPERATION_NOT_ALLOWED_BECAUSE_NOT_IN_ONE_COMPANY("Невозможно выполнить действие, так как пользователь не является сотрудником вашей компании"),
    COMPANY_ADMIN_NOT_ALLOWED_OPERATION("Невозможно выполнить действие, так как вы не являетесь админом всей системы"),
    USER_IS_NOT_OWNER_OF_THEME("Невозможно выполнить, так как текущий пользователь не автор данной темы"),
    USER_IS_NOT_OWNER_OF_INTERVIEW("Невозможно выполнить, так как текущий пользователь не автор данного интервью"),
    CURRENT_USER_NOT_SETTED_COMPANY("Невозможно выполнить действие, так как у вас не установлена компания"),
    USER_IS_NOT_TEST_OWNER("Невозможно выполнить, так как текущий пользователь не является автором теста"),
    USER_IS_NOT_LESSON_OWNER("Невозможно выполнить, так как текущий пользователь не является автором урока"),
    WANTED_USER_NOT_SETTED_COMPANY("Невозможно выполнить действие, так как у данного пользователя не установлена компания"),
    USER_ALREADY_REVIEW_THIS_LESSON("Пользователь уже оставлял отзыв для данного урока"),
    USER_ALREADY_REVIEW_THIS_COURSE("Пользователь уже оценил данный курс"),
    CAN_NOT_GET_REVIEWS_FOR_NOT_PUBLISHED_COURSE("Нельзя получить отзывы для неопубликованного курса"),

    CANT_UNBIND_COURATOR_THIS_IS_THE_ONLY_ONE_COURATOR_ON_COURSE("Нельзя удалить куратора с курса, т.к он единственный куратор"),
    USER_NOT_COMPLETED_COURSE("Пользователь не завершил данный курс"),

    TEST_RESULT_RESPONSE_CONTAINS_NOT_ENOUGH_OR_TOO_MUCH_QUESTIONS("Результат теста содержит не достаточно или слишком много вопросов"),

    QUESTION_DOESNT_HAVE_CORRECT_ANSWER("Для данного вопроса не создан правильный ответ"),

    COURSE_CANNOT_BE_PUBLISHED("Курс не может быть опубликован из-за его некорректности(Неправильна нумерация тем или уроков, курс неактивен или удален)"),

    USER_CANNOT_START_CHAT_WITH_HIMSELF("Пользователь не может начать чат с самим собой"),

    NOT_ALLOWED_EXTENSION("Данное расширение не является допустимым"),

    TEST_ALREADY_USED("Тест уже используется в другом уроке или теме"),

    STACK_CATALOG_CATEGORY_WAS_DELETED("Категория стек каталога была удалена"),

    STACK_CATALOG_CATEGORY_NOT_ACTIVE("Категория стек каталога не активна"),
    LIST_OF_ROLE_SHOULD_CONTAINT_ONLY_ONE_ROLE("У пользователя должна быть только одна роль"),
    PARENT_MESSAGE_SHOULD_BE_IN_SAME_CHAT("Родительское сообщение должно находится в том же чате, что и данное"),

    STACK_CATALOG_CATEGORY_WITH_THIS_NAME_ALREADY_EXISTS("Категория стэк каталогов с такими именем уже существует"),

    EMPLOYEE_ALREADY_IN_COMPANY("Работник уже в компании"),

    EMPLOYEE_NOT_IN_COMPANY("Работник не в компании"),

    USERS_NOT_WORK_IN_THE_SAME_COMPANY("Пользователи не работают в одной компании"),

    USER_NOT_COMPANY_HR("Пользователь передаваемый в качестве HR, не обладает данной ролью"),

    USER_NOT_COMPANY_USER("Пользователь не обладает ролью пользователя от компании(COMPANY_USER)"),

    HR_CAN_DELETE_ONLY_HIS_INTERVIEWS("HR может удалять только те интервью, который создал он сам"),
    INTERVIEW_IS_NOT_PASSED("Интервью еще не пройдено"),
    INTERVIEW_QUESTION_ALREADY_IN_INTERVIEW("В интервью уже есть данный вопрос"),

    INTERVIEW_QUESTION_NOT_IN_QUESTION("Вопроса нет в интервью"),
    HR_CAN_CHANGE_ONLY_HIS_INTERVIEWS("Hr может менять только свои интервью"),

    INTERVIEW_ALREADY_PASSED("Интервью уже закончено"),

    INTERVIEW_QUESTION_USER_SCORE_CANNOT_BE_MORE_THAN_MAX_SCORE("Оценка  пользователя за вопрос на интервью не может превышать максимальной"),

    INTERVIEW_NOT_PASSED("Интервью не было пройдено"),
    USER_HASNT_COMPANY("У юзера нет компании"),

    COURSE_NOT_BELONG_TO_THIS_COMPANY("Курс не принадлежит данной компании"),

    USER_CANNOT_OPERATE_INTERVIEW_CATEGORY_NOT_BELONG_TO_HIS_COMPANY("Нельзя оперировать с  категориями интервью, которые не принадлежат вашей компании"),

    CAN_WAS_DELETED_INTERVIEW_CATEGORY("Категория интервью была удалена"),
    USER_NOT_THEME_AUTHOR("Пользователь не является автором темы"),

    USER_NOT_FILE_AUTHOR("Пользователь не является автором файла"),

    USER_NOT_TEST_AUTHOR("Пользователь не является автором теста"),

    USER_NOT_COURSE_AUTHOR("Пользователь не является автором курса");
    private String message;

    BadRequestError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
