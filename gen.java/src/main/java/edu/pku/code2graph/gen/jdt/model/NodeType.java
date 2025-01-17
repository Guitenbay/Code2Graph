package edu.pku.code2graph.gen.jdt.model;

import edu.pku.code2graph.model.Type;

import static edu.pku.code2graph.model.TypeSet.type;

public class NodeType {
  // Elements
  public static final Type FILE = type("file", true);

  // types
  public static final Type CLASS_DECLARATION = type("class_declaration", true);
  public static final Type INTERFACE_DECLARATION = type("interface_declaration", true);
  public static final Type ENUM_DECLARATION = type("enum_declaration", true);
  public static final Type ANNOTATION_DECLARATION = type("annotation_declaration");

  // members
  public static final Type INIT_BLOCK_DECLARATION = type("init_block_declaration", true);
  public static final Type FIELD_DECLARATION = type("field_declaration", true);
  public static final Type ENUM_CONSTANT_DECLARATION = type("enum_const_declaration", true);
  public static final Type METHOD_DECLARATION = type("method_declaration", true);
  public static final Type VAR_DECLARATION = type("variable_declaration");
  public static final Type SIMPLE_NAME = type("simple_name");
  public static final Type QUALIFIED_NAME = type("qualified_name");
  public static final Type LITERAL = type("literal");
  public static final Type THIS = type("this");

  // Relations
  // block
  public static final Type BLOCK = type("block");

  // expression
  public static final Type ANNOTATION = type("annotation");
  public static final Type ASSIGNMENT = type("assignment");
  public static final Type INFIX = type("infix");
  public static final Type PREFIX = type("prefix");
  public static final Type POSTFIX = type("postfix");

  public static final Type FIELD_ACCESS = type("field_access");
  public static final Type ARRAY_ACCESS = type("array_access");
  public static final Type SUPER_FIELD_ACCESS = type("super_field_access");
  public static final Type METHOD_INVOCATION = type("method_invocation");
  public static final Type SUPER_METHOD_INVOCATION = type("super_method_invocation");
  public static final Type CONSTRUCTOR_INVOCATION = type("constructor_invocation");
  public static final Type SUPER_CONSTRUCTOR_INVOCATION = type("super_constructor_invocation");
  public static final Type PARAMETER_ACCESS = type("parameter_access");
  public static final Type CONSTANT_ACCESS = type("constant_access");
  public static final Type LOCAL_VAR_ACCESS = type("local_var_access");
  public static final Type TYPE_INSTANTIATION = type("type_instantiation");
  public static final Type CAST_EXPRESSION = type("cast_expression");
  public static final Type SWITCH_CASE = type("switch_case");

  public static final Type CATCH_CLAUSE = type("catch_clause");

  // control relations
  public static final Type IF_STATEMENT = type("if_statement");
  public static final Type FOR_STATEMENT = type("for_statement");
  public static final Type ENHANCED_FOR_STATEMENT = type("enhanced_for_statement");
  public static final Type WHILE_STATEMENT = type("while_statement");
  public static final Type DO_STATEMENT = type("do_statement");
  public static final Type SWITCH_STATEMENT = type("switch_statement");
  public static final Type TRY_STATEMENT = type("try_statement");
  public static final Type THROW_STATEMENT = type("throw_statement");

  // inner list node 处理一句代码声明多个变量的情况，这个 inner_list 节点本身没有意义，它的 list 属性里存储多个变量
  public static final Type INNER_LIST = type("inner_list");
}
