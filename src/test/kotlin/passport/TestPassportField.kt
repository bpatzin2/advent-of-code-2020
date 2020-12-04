package passport

import org.junit.Test
import kotlin.test.assertEquals

class TestPassportField {
  @Test
  fun isByrValid() {
    assertEquals(false, isByrValid("a"))
    assertEquals(false, isByrValid("1919"))
    assertEquals(false, isByrValid("2003"))
    assertEquals(true, isByrValid("1920"))
    assertEquals(true, isByrValid("2002"))
    assertEquals(true, isByrValid("2001"))
  }

  @Test
  fun isIyrValid() {
    assertEquals(false, isIyrValid("a"))
    assertEquals(false, isIyrValid("2009"))
    assertEquals(false, isIyrValid("2021"))
    assertEquals(true, isIyrValid("2010"))
    assertEquals(true, isIyrValid("2011"))
    assertEquals(true, isIyrValid("2020"))
  }

  @Test
  fun isEyrValid() {
    assertEquals(false, isEyrValid("a"))
    assertEquals(false, isEyrValid("2019"))
    assertEquals(false, isEyrValid("2031"))
    assertEquals(true, isEyrValid("2020"))
    assertEquals(true, isEyrValid("2021"))
    assertEquals(true, isEyrValid("2030"))
  }

  @Test
  fun isHgtValid() {
    assertEquals(false, isHgtValid("190"))

    assertEquals(false, isHgtValid("190in"))
    assertEquals(false, isHgtValid("58in"))
    assertEquals(true, isHgtValid("59in"))
    assertEquals(true, isHgtValid("76in"))

    assertEquals(false, isHgtValid("149cm"))
    assertEquals(false, isHgtValid("194cm"))
    assertEquals(true, isHgtValid("150cm"))
    assertEquals(true, isHgtValid("193cm"))
  }

  @Test
  fun isHclValid() {
    assertEquals(false, isHclValid("aaaaaa"))
    assertEquals(false, isHclValid("aaaaaaa"))

    assertEquals(false, isHclValid("#Aaaaaa"))
    assertEquals(false, isHclValid("#gaaaaa"))
    assertEquals(false, isHclValid("#,aaaaa"))

    assertEquals(true, isHclValid("#aaaaaa"))
    assertEquals(true, isHclValid("#baaaab"))
    assertEquals(true, isHclValid("#4aaaaf"))

    assertEquals(false, isHclValid("#aaaaa"))
  }

  @Test
  fun isEclValid() {
    assertEquals(false, isEclValid("xyz"))
    assertEquals(true, isEclValid("amb"))
  }

  @Test
  fun isPidValid() {
    assertEquals(true, isPidValid("012345678"))
    assertEquals(false, isPidValid("a12345678"))
    assertEquals(false, isPidValid("12345678"))
  }

}