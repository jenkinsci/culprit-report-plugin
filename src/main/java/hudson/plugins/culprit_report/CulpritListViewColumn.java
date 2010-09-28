/*
 * The MIT License
 *
 * Copyright (c) 2010, CollabNet
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
package hudson.plugins.culprit_report;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Item;
import hudson.model.User;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.Collection;
import java.util.Collections;

/**
 * Shows the list of culprits in the list view column.
 *
 * @author Kohsuke Kawaguchi
 */
public class CulpritListViewColumn extends ListViewColumn {
    @DataBoundConstructor
    public CulpritListViewColumn() {
    }

    public Collection<User> getCulpritsOf(Item item) {
        if (item instanceof AbstractProject) {
            AbstractProject<?,?> j = (AbstractProject) item;
            AbstractBuild b = j.getLastBuild();
            if (b!=null)
                return b.getCulprits();
        }

        return Collections.emptyList();
    }

    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {
        @Override
        public String getDisplayName() {
            return Messages.CulpritListViewColumn_DisplayName();
        }
    }
}
