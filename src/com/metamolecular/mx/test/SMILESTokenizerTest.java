/*
 * MX Cheminformatics Tools for Java
 * 
 * Copyright (c) 2007, 2008 Metamolecular, LLC
 * 
 * http://metamolecular.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.metamolecular.mx.test;

import com.metamolecular.mx.io.smiles.SMILESTokenizer;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 * @author Richard L. Apodaca
 */
public class SMILESTokenizerTest extends TestCase
{

  private List<String> tokens;

  @Override
  protected void setUp() throws Exception
  {
    tokens = new ArrayList<String>();
  }

  public void testItShouldFindAllTokensInALinearChain()
  {
    SMILESTokenizer tokenizer = new SMILESTokenizer("CCCCCC");

    tokens.clear();

    while (tokenizer.hasNextToken())
    {
      tokens.add(tokenizer.nextToken());
    }

    assertEquals(6, tokens.size());

    for (String token : tokens)
    {
      assertEquals("C", token);
    }
  }

  public void testItShouldFindAllTokensInACycle()
  {
    SMILESTokenizer tokenizer = new SMILESTokenizer("C1CCCCC1");
    String[] tokens = new String[]{"C", "1", "C", "C", "C", "C", "C", "1"};
    int index = 0;
    
    while (tokenizer.hasNextToken())
    {
      String token = tokenizer.nextToken();
      
      assertEquals(tokens[index], token);
      
      index++;
    }
  }
}
