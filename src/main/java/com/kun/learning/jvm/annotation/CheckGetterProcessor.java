package com.kun.learning.jvm.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * @Author: jrjiakun
 * @Date: 2019/1/24 13:59
 *
 * 所有的注解处理器都要实现Processor接口
 *
 * init方法用来存储注解处理器的初始化代码。由于利用反射api, 每个注解处理器都需要定义一个无参构造器
 *
 * getSupportedSourceVersion: 返回该处理器所支持的java
 *
 * process: 注解处理方法
 *
 * AbstractProcessor： jdk提供的实现了Processor接口的抽象类，抽象实现了init, getSupportedAnnotationTypes和getSupportedSourceVersion方法
 *
 * 其子类可以通过@SupportedAnnotationTypes和getSupoortedSourceVersion方法
 *
 *
 * 如何使用呢？
 * 方法1： javac -processor
 *
 * $ javac -cp /CLASSPATH/TO/CheckGetterProcessor -processor bar.CheckGetterProcessor Foo.java
 * error: Class 'Foo' is annotated as @CheckGetter, but field 'a' is without getter
 * 1 error
 *
 * 方法2：
 * 将注解处理器编译生成的class文件压缩如jar中，然后再jar的配置文件中记录该注解处理器的包名及类名
 *
 *
 */
@SupportedAnnotationTypes("com.kun.learning.jvm.annotation.CheckGetter")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CheckGetterProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // TODO: annotated ElementKind.FIELD
        for (TypeElement annotatedClass : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(CheckGetter.class))) {
            for (VariableElement field : ElementFilter.fieldsIn(annotatedClass.getEnclosedElements())) {
                if (!containsGetter(annotatedClass, field.getSimpleName().toString())) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            String.format("getter not found for '%s.%s'.", annotatedClass.getSimpleName(), field.getSimpleName()));
                }
            }
        }
        return true;
    }

    private static boolean containsGetter(TypeElement typeElement, String name) {
        String getter = "get" + name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        for (ExecutableElement executableElement : ElementFilter.methodsIn(typeElement.getEnclosedElements())) {
            if (!executableElement.getModifiers().contains(Modifier.STATIC)
                    && executableElement.getSimpleName().toString().equals(getter)
                    && executableElement.getParameters().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
