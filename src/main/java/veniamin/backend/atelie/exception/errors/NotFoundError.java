package veniamin.backend.atelie.exception.errors;

public enum NotFoundError {
    USER_NOT_FOUND("Пользователь не был найден"),

    COURSE_NOT_FOUND("Курс не найден"),
    THEME_IN_COURSE_NOT_FOUND("Тема в данном курсе не найдена"),
    TEST_RESULT_NOT_FOUND("Результат теста не найден"),
    CHAT_NOT_FOUND("Чат не найден"),
    MESSAGE_NOT_FOUND("Сообщение не найдено"),
    PROMOCODE_NOT_FOUND("Промокод не найден"),
    FILE_NOT_FOUND("Файл не найден"),

    ORDER_NOT_FOUND("Заказ не найден"),
    TEST_NOT_FOUND("Тест не найден"),

    QUESTION_NOT_FOUND("Вопрос не найден"),
    QUESTION_ANSWER_NOT_FOUND("Ответ пользователя не найден"),

    ANSWER_NOT_FOUND("Ответ не найден"),


    THEME_NOT_FOUND("Тема не найдена"),

    LESSON_NOT_FOUND("Урок не найден"),
    INFO_PAGE_NOT_FOUND("Информационная страница не найдена"),
    CONFIG_PROPERTY_NOT_FOUND("Проперти конфига не была найдена"),

    REVIEW_NOT_FOUND("Отзыв не найден"),

    STACK_CATALOG_NOT_FOUND("Позиция стека не найдена"),

    STACK_CATALOG_CATEGORY_NOT_FOUND("Категория для stack catalog не найдена"),

    COMPANY_NOT_FOUND("Компания не найдена"),

    INTERVIEW_NOT_FOUND("Собеседование не найдено"),

    INTERVIEW_QUESTION_NOT_FOUND("Вопрос собеседования не найден"),
    GRADING_NOT_FOUND("Грейдинг не найден"),
    INTERVIEW_PATTERN_NOT_FOUND("Шаблон собеседования не найден"),
    INTERVIEW_PURPOSE_NOT_FOUND("Цель для собеседования не найдена"),
    INTERVIEW_CATEGORY_NOT_FOUND("Категория интервью не найдена");

    private String message;

    NotFoundError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
