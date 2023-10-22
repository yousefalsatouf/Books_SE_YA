package be.isl.books.ui.viewmodel;

import be.isl.books.entity.Comment;

public class CommentViewModel {
    private Comment comment;

    public CommentViewModel(Comment comment) {
        this.comment = comment;
    }

    public int getCommentId() {
        return comment.getCommentId();
    }

    /*public int getBookId() {
        return comment.getBookId();
    }*/

    public String getComment() {
        return comment.getComment();
    }

    public int getStars() {
        return comment.getStars();
    }

    @Override
    public String toString() {
        return "Comment ID: " + getCommentId() + " | Comment: " + getComment() + " | Stars: " + getStars();
    }
}
