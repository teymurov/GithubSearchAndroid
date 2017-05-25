package ru.teymurov.githubsearch.mvp.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ru.teymurov.githubsearch.R;
import ru.teymurov.githubsearch.databinding.ItemProgressBarBinding;
import ru.teymurov.githubsearch.databinding.ItemRepositoryBinding;
import ru.teymurov.githubsearch.retrofit.gson.Owner;
import ru.teymurov.githubsearch.retrofit.gson.Repository;

public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_REPOSITORY = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;

    private boolean mMaybeMore;
    private List<Repository> mRepositories;
    private OnScrollToBottomListener mScrollToBottomListener;

    public RepositoryAdapter(OnScrollToBottomListener listener) {
        mRepositories = new ArrayList<>();
        mScrollToBottomListener = listener;
    }

    public void addRepositories(List<Repository> repositories, boolean maybeMore) {
        mRepositories.addAll(repositories);
        dataSetChanged(maybeMore);
    }

    public void setRepositories(List<Repository> repositories, boolean maybeMore) {
        mRepositories = new ArrayList<>(repositories);
        dataSetChanged(maybeMore);
    }

    private void dataSetChanged(boolean maybeMore) {
        mMaybeMore = maybeMore;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position == mRepositories.size() ? VIEW_TYPE_PROGRESS : VIEW_TYPE_REPOSITORY;
    }

    @Override
    public int getItemCount() {
        return mRepositories.size() + (mMaybeMore ? 1 : 0);
    }

    public int getRepositoriesCount() {
        return mRepositories.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_REPOSITORY) {
            ItemRepositoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_repository, parent, false);
            return new RepositoryViewHolder(binding);
        } else {
            ItemProgressBarBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_progress_bar, parent, false);
            return new ProgressViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_REPOSITORY) {
            onBindRepository((RepositoryViewHolder) holder, position);
        } else {
            onBindProgressBar((ProgressViewHolder) holder);
        }
    }

    private void onBindRepository(RepositoryViewHolder holder, int position){
        final ItemRepositoryBinding binding = holder.getBinding();
        final Repository repository = mRepositories.get(position);
        final Owner owner = repository.getOwner();
        final String avatarUrl = owner == null ? null : owner.getAvatarUrl();

        binding.title.setText(repository.getFullName());
        binding.description.setText(repository.getDescription());

        Glide
                .with(binding.getRoot().getContext())
                .load(avatarUrl)
                .into(binding.avatarOwner);
    }

    private void onBindProgressBar(ProgressViewHolder holder) {
        holder.getBinding().progressBar.setIndeterminate(true);
        mScrollToBottomListener.onScrollToBottom();
    }

    private class RepositoryViewHolder extends RecyclerView.ViewHolder {
        private ItemRepositoryBinding mBinding;

        RepositoryViewHolder(ItemRepositoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        ItemRepositoryBinding getBinding() {
            return mBinding;
        }
    }

    private class ProgressViewHolder extends RecyclerView.ViewHolder {
        private ItemProgressBarBinding mBinding;

        ProgressViewHolder(ItemProgressBarBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        ItemProgressBarBinding getBinding() {
            return mBinding;
        }
    }

    public interface OnScrollToBottomListener {
        void onScrollToBottom();
    }
}