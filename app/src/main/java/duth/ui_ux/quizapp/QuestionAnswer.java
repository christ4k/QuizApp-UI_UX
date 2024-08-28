package duth.ui_ux.quizapp;

public class QuestionAnswer {
    public static String[] question = {
            "How much does it cost ...",
            "How invented ...",
            "How is the creator of ...",
            "Which OS will you use for ..."
    };

    public static String[][] choices = {
            {"32", "42", "52", "62"},
            {"Graham Bell", "Einstein", "Edison", "None of the above"},
            {"Bill Gates", "Elon Musk", "Jeff Bezos", "Steve Jobs"},
            {"Linux", "FreeBSD", "OpenBSD", "MacOS", "Windows"}
    };

    public static String[] correctAnswers = {
            "42",
            "Einstein",
            "Elon Musk",
            "FreeBSD"
    };
}
