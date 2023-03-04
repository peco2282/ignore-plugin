package com.github.peco2282.gitignore

enum class GitIgnoreType(private val langType: String) {
  ANDROID("Android"),
  APPENGINE("AppEngine"),
  CPP("C++"),
  C("C"),
  CAKEPHP("CakePHP"),
  CLOJURE("Clojure"),
  CMAKE("CMake"),
  CUDA("CUDA"),
  D("D"),
  DART("Dart"),
  ELIXIR("Elixir"),
  FORTRAN("Fortran"),
  GO("Go"),
  GRADLE("Gradle"),
  HASKELL("Haskell"),
  JAVA("Java"),
//  JAVASCRIPT(JAVA.getLangType()),
  JEKYLL("Jekyll"),
  JENKINS_HOME("JENKINS_HOME"),
  JETBRAINS("JetBrains"),
  JULIA("Julia"),
  KOTLIN("Kotlin"),
  LARAVEL("Laravel"),
  LUA("Lua"),
  MAVEN("Maven"),
  NODE("Node"),
  OBJECTIVE_C("Objective-C"),
  PERL("Perl"),
  PROCESSING("Processing"),
  PYTHON("Python"),
  R("R"),
  RAILS("Rails"),
  RUBY("Ruby"),
  RUST("Rust"),
  SCALA("Scala"),
  SWIFT("Swift"),
  TEX("TeX"),
  UNITY("Unity"),
  UNREAL_ENGINE("UnrealEngine"),
  VISUAL_STUDIO("VisualStudio"),
  WORDPRESS("WordPress")
  ;

  fun getLangType() : String {
    return this.langType
  }
  fun getFileName() : String {
    return this.langType + ".gitignore";
  }

  fun getDescription() : String {
    return "gitignore for " + this.langType
  }
}