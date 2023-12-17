package com.example.demo;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.CustomAnnotations.ClassDocumentation;
import com.example.demo.CustomAnnotations.MethodDocumentation;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;

public class DocumentationScanner {
    //java_documents store all java docs(comments)
    static List<String> Java_documents = new ArrayList<String>();
    public static void main(String[] args) {
        GetClassesAndMethodsAnnotated();
    }
    public static void GetClassesAndMethodsAnnotated() {
        try {
            //getting paths of the all .java classes from defined path and sending them to getjavadoc method to het docs
            Files.walk(Paths.get("C:\\Users\\immadisetty.deepika\\Downloads\\Javadoc_finder\\demo\\src\\main\\java\\com\\example\\demo\\DemoClasses"))
                    .filter(Files::isRegularFile)
                    .filter(files -> files.toString().endsWith(".java"))
                    .forEach(DocumentationScanner::GetJavaDoc);
        } catch (IOException e) {
            System.out.println("cannot able to read file !!!");
        }
    }
    public static void GetJavaDoc(Path p)
    {

        Path javadoc = Paths.get("C:\\Users\\immadisetty.deepika\\Downloads\\Javadoc_finder\\demo\\src\\main\\resources\\javadoc.txt");
        try
        {
            //generating a compileunit for each file
            CompilationUnit compileunit = JavaParser.parse(p.toFile());
            for (TypeDeclaration type : compileunit.getTypes()) {
                TypeDeclaration typeDeclaration = (TypeDeclaration) type;
                String typeName = typeDeclaration.getName();
                //checking weather class has class Annotation or not
                boolean hasClassDocAnnotation = typeHasClassDocumentationAnnotation(typeDeclaration);
                System.out.println("Class " + typeName + (hasClassDocAnnotation ? " contains " : " doesn't contains ") + " @ClassDocumentation annotation");
                //extracting the Javadoc comment
                Comment commentOpt = typeDeclaration.getComment();
                if (commentOpt instanceof JavadocComment) {
                    JavadocComment comment = (JavadocComment) commentOpt;
                    String doc = "Class " + typeName +  (hasClassDocAnnotation ? "" : " doesn't") + " contains JavaDoc comment:\n" + comment + "\n";
                    Java_documents.add(doc);
                } else {
                    System.out.println("Class " + typeName + " doesn't contain JavaDoc comment");
                }
                for (BodyDeclaration classmember : type.getMembers()) {
                    // iterating over members of class
                    if (classmember instanceof MethodDeclaration) {
                        MethodDeclaration method = (MethodDeclaration) classmember;
                        //checking if member of class  is a method or not
                        String methodName = method.getName();
                        String className = type.getName();
                        boolean hasMethodDocAnnotation = methodHasMethodDocumentationAnnotation(method);
                        System.out.println("Method " + methodName + " in class " + className +
                                (hasMethodDocAnnotation ? " contains" : " doesn't contains ") + " @MethodDocumentation annotation");
                        commentOpt = method.getComment();
                        if (commentOpt instanceof JavadocComment) {
                            JavadocComment comment = (JavadocComment) commentOpt;
                            String doc = "Method " + methodName + " in class " + className +
                                    " has JavaDoc comment:\n" + comment.toString() + "\n";
                            Java_documents.add(doc);
                        } else {
                            System.out.println("Method " + methodName + " in class " + className +
                                    " doesn't have any JavaDoc comment");
                        }
                    }
                }
                System.out.println();
            }
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            //writing into file
            Files.write(javadoc,Java_documents, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        catch (IOException e) {
            System.out.println("Couldn't able  to write output file: " + e.getMessage());
        }
    }
    private static boolean typeHasClassDocumentationAnnotation(TypeDeclaration type) {
        return type.getAnnotations().stream()
                .anyMatch(a -> a.getName().getName().equals(ClassDocumentation.class.getSimpleName()));
    }
    private static boolean methodHasMethodDocumentationAnnotation(MethodDeclaration method) {
        return method.getAnnotations().stream()
                .anyMatch(a -> a.getName().getName().equals(MethodDocumentation.class.getSimpleName()));
    }


}
